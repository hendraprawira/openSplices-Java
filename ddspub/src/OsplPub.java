import HelloWorldData.Msg;
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

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.TimeoutException;

public class OsplPub extends Thread{
    @Override
    public void run(){
        boolean autodispose_unregistered_instances = false;
        System.setProperty(
                ServiceEnvironment.IMPLEMENTATION_CLASS_NAME_PROPERTY,
                "org.opensplice.dds.core.OsplServiceEnvironment");
        ServiceEnvironment env = ServiceEnvironment
                .createInstance(Main.class.getClassLoader());

        DomainParticipantFactory dpf = DomainParticipantFactory
                .getInstance(env);

        DomainParticipant p = dpf.createParticipant();
        PolicyFactory policyFactory = env.getSPI().getPolicyFactory();

        Reliability r = PolicyFactory.getPolicyFactory(env).Reliability()
                .withBestEffort();
        Durability d = PolicyFactory.getPolicyFactory(env).Durability()
                .withTransient();

        TopicQos topicQos = p.getDefaultTopicQos().withPolicies(r,d);
        Collection<Class<? extends Status>> statuses = new HashSet<Class<? extends Status>>();
        Topic<Msg> topic = p.createTopic("HelloWorldData_Msg12", Msg.class, topicQos, null , statuses);

        Partition partition = PolicyFactory.getPolicyFactory(env).Partition()
                .withName("HelloWorld example");

        PublisherQos pubQos = p.getDefaultPublisherQos().withPolicy(partition);
        Publisher pub = p.createPublisher(pubQos);

        WriterDataLifecycle wdlq = policyFactory.WriterDataLifecycle()
                .withAutDisposeUnregisteredInstances(autodispose_unregistered_instances);
        DataWriterQos dwQos = pub.copyFromTopicQos(pub.getDefaultDataWriterQos().withPolicy(wdlq),topic.getQos());

        DataWriter<Msg> writer = pub.createDataWriter(topic,dwQos);
        Msg msgSample = new Msg();
        try {
            writer.registerInstance(msgSample);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
        for (int i = 100; i < 10000; i++) {
            msgSample.userID = i;
            msgSample.message = "" + i;

            try {
                writer.write(msgSample, InstanceHandle.nilHandle(env));
                System.out.println("| Publish Message : " + msgSample.message +"\n");
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        p.close();
    }

}
