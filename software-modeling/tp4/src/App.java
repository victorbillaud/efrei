import java.util.ArrayList;

import course.Course;
import participant.Participant;

public class App {
    public static void main(String[] args) throws Exception {

        Participant participant = new Participant("John", 20);
        ArrayList<Participant> participants = new ArrayList<Participant>();
        participants.add(participant);

        Course course = new Course("Java", 10.0f, 20.0f, participants);
        System.out.println(course.toString());
        course.getParticipantsManager().removeParticipant(participant);
        System.out.println(course.toString());
    }
    
}
