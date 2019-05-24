import com.amazonaws.regions.*;
import com.amazonaws.auth.*;
import com.amazonaws.services.sns.*;
import com.amazonaws.services.sns.model.*;

public class SNS {
   public static void main(String args[]) {
	// Publish a message to an Amazon SNS topic.
        if (args.length < 5) {
	    System.err.println("USAGE: java SNS arn msg sub access secret\n\tarn\tTopic ARN\n\tmsg\tSNS message\n\tsub\tSNS subject\n\taccess\tAWS access_key\n\tsecret\tAWS secret_key\n");
	    System.exit(1);
        }
        String topicARN = args[0];
        String msg = args[1];
        String sub = args[2];
        String awsAccess = args[3];
        String awsSecret = args[4];

        //arn:aws:sns:us-west-2:1223443838:testtopic
        String[] splitARN = topicARN.split(":");
        String region = splitARN[3];

        AmazonSNSClient snsClient = new AmazonSNSClient(new BasicAWSCredentials(awsAccess, awsSecret));
        snsClient.setRegion(Region.getRegion(Regions.fromName(region)));

	PublishRequest publishRequest = new PublishRequest(topicARN, msg);
	PublishResult publishResponse = snsClient.publish(publishRequest);

	// Print the MessageId of the message.
	System.out.println("MessageId: " + publishResponse.getMessageId());
   }
}
