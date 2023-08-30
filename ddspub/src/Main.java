import java.util.concurrent.TimeoutException;
import java.util.HashSet;
import java.util.Collection;

import org.omg.dds.core.InstanceHandle;
import org.omg.dds.core.ServiceEnvironment;
import org.omg.dds.core.policy.*;
import org.omg.dds.core.status.Status;
import org.omg.dds.domain.DomainParticipant;
import org.omg.dds.domain.DomainParticipantFactory;
import org.omg.dds.pub.DataWriter;
import org.omg.dds.pub.DataWriterQos;
import org.omg.dds.pub.Publisher;
import org.omg.dds.pub.PublisherQos;
import org.omg.dds.topic.Topic;
import org.omg.dds.topic.TopicQos;

import HelloWorldData.Msg;

public class Main {
    public static void main(String[] args){
        OsplPub osplPub = new OsplPub();
        osplPub.start();
    }
}