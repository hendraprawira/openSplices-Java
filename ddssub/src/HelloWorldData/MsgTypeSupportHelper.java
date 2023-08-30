package HelloWorldData;

import org.opensplice.dds.dcps.Utilities;

public final class MsgTypeSupportHelper
{

    public static MsgTypeSupport narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgTypeSupport) {
            return (MsgTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

    public static MsgTypeSupport unchecked_narrow(Object obj)
    {
        if (obj == null) {
            return null;
        } else if (obj instanceof MsgTypeSupport) {
            return (MsgTypeSupport)obj;
        } else {
            throw Utilities.createException(Utilities.EXCEPTION_TYPE_BAD_PARAM, null);
        }
    }

}
