package course;

import java.util.ArrayList;

import participant.Participant;
import participant.ParticipantsManager;

public class Course {
    private String name;
    private Float distance;
    private Float duration;
    private ParticipantsManager participantsManager;

    public Course(String name, Float distance, Float duration, ArrayList<Participant> participants) {
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.participantsManager = new ParticipantsManager(participants);
    }
    
    public ParticipantsManager getParticipantsManager() {
        return participantsManager;
    }

    @Override
    public String toString() {
        return "Course [name=" + name + ", distance=" + distance + ", duration=" + duration + ", participants="
                + participantsManager.getParticipants().size() + ", participantsManager=" + participantsManager + "]";
    }

}
