package HelloWorldData;

public interface MsgDataWriterOperations extends
    DDS.DataWriterOperations
{

    long register_instance(
            Msg instance_data);

    long register_instance_w_timestamp(
            Msg instance_data,
            DDS.Time_t source_timestamp);

    int unregister_instance(
            Msg instance_data,
            long handle);

    int unregister_instance_w_timestamp(
            Msg instance_data,
            long handle, 
            DDS.Time_t source_timestamp);

    int write(
            Msg instance_data,
            long handle);

    int write_w_timestamp(
            Msg instance_data,
            long handle, 
            DDS.Time_t source_timestamp);

    int dispose(
            Msg instance_data,
            long instance_handle);

    int dispose_w_timestamp(
            Msg instance_data,
            long instance_handle, 
            DDS.Time_t source_timestamp);
    
    int writedispose(
            Msg instance_data,
            long instance_handle);

    int writedispose_w_timestamp(
            Msg instance_data,
            long instance_handle, 
            DDS.Time_t source_timestamp);

    int get_key_value(
            MsgHolder key_holder,
            long handle);
    
    long lookup_instance(
            Msg instance_data);

}
