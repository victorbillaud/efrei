package participant;

import java.util.ArrayList;

public class ParticipantsManager {
    private ArrayList<Participant> participants;
    
    public ParticipantsManager(ArrayList<Participant> participants) {
        this.participants = new ArrayList<Participant>(participants);
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        this.participants.remove(participant);
    }
}
