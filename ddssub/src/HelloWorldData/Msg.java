package HelloWorldData;

public final class Msg {

    public int userID;
    public String message = "";

    public Msg() {
    }

    public Msg(
        int _userID,
        String _message)
    {
        userID = _userID;
        message = _message;
    }

}
