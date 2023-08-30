package HelloWorldData;

import org.opensplice.dds.dcps.Utilities;

public final class MsgDataReaderHelper
{

    public static MsgDataReader narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgDataReader) {
            return (MsgDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MsgDataReader unchecked_narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgDataReader) {
            return (MsgDataReader)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
