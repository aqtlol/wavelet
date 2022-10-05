import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

class daHandler implements URLHandler {
    // Code copied and changed from NumberServer.java provided by CSE15l

    // Recieved help from Angela in my lab group
    
    int num = 0;
    ArrayList<String> sList = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Number: %d", num);
        } 
        else if (url.getPath().equals("/search")) {
            // return "search works";
            String searchChar = url.getQuery().split("=")[1];
            ArrayList<String> output = new ArrayList<>();
            for(int i = 0; i < sList.size(); i++) {
                if(sList.get(i).contains(searchChar)) {
                    output.add(sList.get(i));
                }
            }
            return Arrays.toString(output.toArray());
        } 
        else {
            // System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                // return "add works";
                String[] parameters = url.getQuery().split("="); // My changes are below to the add method.
                // if (parameters[0].equals("s")) {
                     sList.add(parameters[1]);
                    return Arrays.toString(sList.toArray());
               // }
            }
            return "Error bro";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new daHandler());
    }
}

