import org.junit.BeforeClass;
import org.junit.Test;
import solution.Person;
import solution.Person.SEX;
import solution.SecretSanta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestOptionOne extends SecretSanta{

    static List<Person> participants = new ArrayList<Person>();

    @SuppressWarnings("Duplicates") @BeforeClass
    public static void setup(){
        participants.add(new Person("Marlon", SEX.MALE));
        participants.add(new Person("Caroline", SEX.FEMALE));
        participants.add(new Person("Mookie", SEX.MALE));
        participants.add(new Person("Barkley", SEX.MALE));
        participants.add(new Person("Appy", SEX.MALE));
        participants.add(new Person("Poppy", SEX.FEMALE));
        participants.add(new Person("Rachel", SEX.FEMALE));
    }

    @Test
    public void testSenderCannotGiftSelf(){
        Map<Person, Person> secretSenderReceiver = getSecretSantaList(participants);
        for(Map.Entry<Person, Person> entry : secretSenderReceiver.entrySet()){
            System.out.println("Sender = " + entry.getKey().getFirstName() +
                    ", Recipient = " + entry.getValue().getFirstName());
        }
    }
}
