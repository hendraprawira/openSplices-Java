package HelloWorldData;

public interface MsgTypeSupportOperations extends
    DDS.TypeSupportOperations
{
    @Override
    int register_type(
            DDS.DomainParticipant participant, 
            String type_name);

}
