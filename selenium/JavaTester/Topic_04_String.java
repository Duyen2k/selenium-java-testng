package JavaTester;

public class Topic_04_String {
    public static void main(String[] args) {
        String url = "https://the-internet.herokuapp.com/basic_auth";
        String username = "admin";
        String password = "admin";
        String[] urlArray = url.split("//");
        url = urlArray[0] + "//" + username + ":" + password + "@" + urlArray[1];
    }

    //setTimeout(() => {debugger;}, 3000);  (debug)
}