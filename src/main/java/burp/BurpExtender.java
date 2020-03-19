package burp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.securityevaluators.burp2vegeta.Header;
import com.securityevaluators.burp2vegeta.VegetaDefinition;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BurpExtender implements IBurpExtender, IContextMenuFactory
{
    private PrintWriter stdout;
    private PrintWriter stderr;
    private IExtensionHelpers helpers;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        // set our extension name
        callbacks.setExtensionName("Burp2Vegeta");

        // obtain our output and error streams
        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);
        helpers = callbacks.getHelpers();

        callbacks.registerContextMenuFactory(this);
        stdout.println("Loaded Burp2Vegeta extension");
    }

    @Override
    public List<JMenuItem> createMenuItems(IContextMenuInvocation iContextMenuInvocation) {
        int itemCount = iContextMenuInvocation.getSelectedMessages().length;
        if (itemCount < 1) return null;
        JMenuItem exportItem = new JMenuItem("Copy as Vegeta Attack Definition");
        exportItem.addActionListener((e) -> exportItems(iContextMenuInvocation.getSelectedMessages()));
        return Collections.singletonList(exportItem);
    }

    private void exportItems(IHttpRequestResponse[] items) {
        try {
            stdout.println("Saving " + items.length + " items");
            Gson gson = new GsonBuilder().create();
            String entries = Arrays.stream(items)
                    .map(this::convertRequestToDefinition)
                    .map(gson::toJson)
                    .collect(Collectors.joining(System.lineSeparator()));
            stdout.println("Result:");
            stdout.println(entries);
            entries += System.lineSeparator(); //Add a final line separator since Vegeta expects a final newline
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(entries), null);
        }
        catch(Exception e) {
            e.printStackTrace(stderr);
        }
    }

    private VegetaDefinition convertRequestToDefinition(IHttpRequestResponse requestResponse) {
        IRequestInfo requestInfo = helpers.analyzeRequest(requestResponse);
        Map<String, List<String>> headers = requestInfo.getHeaders().stream()
                .skip(1) //skip the verb line
                .map(Header::Parse) //parse each header
                .collect( //group by...
                        Collectors.groupingBy(Header::getName, //header name into...
                        Collectors.mapping(Header::getContent, Collectors.toList()))); //a map of header name to values
        int offset = requestInfo.getBodyOffset();
        byte[] request = requestResponse.getRequest();
        byte[] body = Arrays.copyOfRange(request, offset, request.length);
        return new VegetaDefinition(
                body.length > 0 ? body : null,
                headers,
                requestInfo.getMethod(),
                requestInfo.getUrl().toString()
        );
    }
}