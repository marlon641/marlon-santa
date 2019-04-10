import org.junit.BeforeClass;
import org.junit.Test;
import solution.Person;
import solution.Person.SEX;
import solution.SecretSanta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestOptionTwo extends SecretSanta{

    static List<Person> participants = new ArrayList<Person>();

    @SuppressWarnings("Duplicates") @BeforeClass
    public static void setup(){
        participants.add(new Person("Marlon", SEX.MALE));
        participants.add(new Person("Rachel", SEX.FEMALE));
        participants.add(new Person("Mookie", SEX.MALE));
        participants.add(new Person("Marky", SEX.MALE));
        participants.add(new Person("Matt", SEX.MALE));
        participants.add(new Person("BayBay", SEX.FEMALE));
        participants.add(new Person("Lambchop", SEX.FEMALE));
        participants.add(new Person("Cocoa", SEX.FEMALE));
        participants.add(new Person("Dave", SEX.MALE));
        participants.add(new Person("Jerry", SEX.MALE));

    }

    @Test
    public void testSenderCannotSendGiftToLastThreeYearsRecipients(){
        setGameYear(2015);
        List<Person> list2015 = new ArrayList<Person>();
        list2015.addAll(participants);
        Map<Person, Person> secretSenderReceiver =
                getSecretSantaListButLastThreeYears(list2015, participants.size());
        printSecretList(secretSenderReceiver, getGameYear());
        setGameYear(2016);
        List<Person> list2016 = new ArrayList<Person>();
        list2016.addAll(participants);
        secretSenderReceiver = getSecretSantaListButLastThreeYears(list2016, participants.size());
        printSecretList(secretSenderReceiver, getGameYear());
        setGameYear(2017);
        List<Person> list2017 = new ArrayList<Person>();
        list2017.addAll(participants);
        secretSenderReceiver = getSecretSantaListButLastThreeYears(list2017, participants.size());
        printSecretList(secretSenderReceiver, getGameYear());
    }

    public void printSecretList(Map<Person, Person> secretSenderReceiver, int year){
        System.out.println("Year: " + year);
        for(Map.Entry<Person, Person> entry : secretSenderReceiver.entrySet()){
            System.out.println("Sender = " + entry.getKey().getFirstName() +
                    ", Recipient = " + entry.getValue().getFirstName());
        }
    }
}
