package HelloWorldData;

import org.opensplice.dds.dcps.Utilities;

public final class MsgDataWriterHelper
{

    public static MsgDataWriter narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgDataWriter) {
            return (MsgDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MsgDataWriter unchecked_narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgDataWriter) {
            return (MsgDataWriter)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
