package HelloWorldData;

import org.opensplice.dds.dcps.Utilities;

public final class MsgDataReaderViewHelper
{

    public static MsgDataReaderView narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgDataReaderView) {
            return (MsgDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MsgDataReaderView unchecked_narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgDataReaderView) {
            return (MsgDataReaderView)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
