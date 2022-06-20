import java.lang.reflect.Array;
import java.util.*;

/**
 * A class that represents the community and simulates
 * the running of the lottery.
 */
public final class CommunityLSim {

    ArrayList<CommunityMember> communityMembers;
    Random random = new Random();
    //you will need to add more instance variables

    /**
     * Creates a new Community with the specified number of people
     * @param numP The number of people in the community
     */
    public CommunityLSim( int numP) {
        //create the players
        this.communityMembers = new ArrayList<>();

        for (int i = 0; i < numP; i++) {
            if (i < numP /2.0)
                this.communityMembers.add(new CommunityMember(CMemberKind.POORLY_PAID, (float)(99+Math.random())));
            else
                this.communityMembers.add(new CommunityMember(CMemberKind.WELL_PAID, (float)(100.1+Math.random())));
        }

    }

    public int getSize() {
        return this.communityMembers.size();
    }

    public CommunityMember getPlayer(int i) {
        return this.communityMembers.get(i);
    }

    /**
     * Give each community member some pocket change.
     * Give POORLY_PAID community members 0.03f, and give
     * WELL_PAID community members 0.1f.
     */
    // TODO: Implement this method.
    public void addPocketChange() {
        for (CommunityMember communityMember : this.communityMembers){
            if (communityMember.getKind() == CMemberKind.WELL_PAID){
                communityMember.setMoney(communityMember.getMoney() + 0.1f);
            } else {
                communityMember.setMoney(communityMember.getMoney() + 0.03f);
            }
        }
    }

    // TODO: Implement this method. I added this
    public void addLotteryEarning() {
        Game game = new Game();
        ArrayList<Integer> playerIndices = reDoWhoPlays();
        for (int playerIndex : playerIndices) {
            CommunityMember player = getPlayer(playerIndex);
            float winningMoney = game.play(playRandom());
            player.setMoney(player.getMoney() + winningMoney);

            if (winningMoney == -1)
                randomRedistributed();
        }
    }

    public void randomRedistributed() {
        if (random.nextInt(0,101) <= 70) {
            CommunityMember player = getPlayer(random.nextInt(15, 30));
            player.setMoney(player.getMoney() + 1);
        } else {
            CommunityMember player = getPlayer(random.nextInt(0, 15));
            player.setMoney(player.getMoney() + 1);
        }
    }

    // TODO: Write a method that computes a new list of lottery players,
    //  choosing from the list of community members.
    //  You will likely want to change this method signature.
    private ArrayList<Integer> reDoWhoPlays() {
        ArrayList<Integer> whoPlay = new ArrayList<>();
        randomUniqIndx(whoPlay, 9, 0, 15);
        randomUniqIndx(whoPlay, 6, 15, 30);
        return whoPlay;
    }

    /* generate some random indices for who might play the lottery
        in a given range of indices */

    /**
     * Generate a number of random indices within an interval
     * @param numI The number of random unique indices to generate
     * @param startRange The lower bound of the interval, inclusive
     * @param endRange The upper bound of the interval, exclusive
     */
    // TODO: Implement this method. You will likely want to change this
    //  method signature.
    public void randomUniqIndx(ArrayList<Integer> whoPlay, int numI, int startRange, int endRange) {
        int count = 0;
        do {
            int number = random.nextInt(startRange, endRange);
            if (!whoPlay.contains(number)) {
                whoPlay.add(number);
                count += 1;
            }
        }while(count != numI);
    }

    // TODO: Implement this method. I added this
    public HashSet playRandom() {
        HashSet fiveRandom = new HashSet();
        do {
            int number = random.nextInt(43);
            if (!fiveRandom.contains(number))
                fiveRandom.add(number);
        }while(fiveRandom.size() != 5);

        return fiveRandom;
    }

    // TODO: Implement this method. I added this
    public void addMoneyOverTime() {
        for (CommunityMember communityMember : communityMembers) {
            communityMember.updateMoneyEachYear();
        }
    }

    // TODO: Implement this method. I added this
    public void printRichestPoorest(int year) {
        double richest = Double.MIN_VALUE;
        double poorest = Double.MAX_VALUE;
        for (CommunityMember communityMember : communityMembers) {
            if (communityMember.getMoney() > richest) {
                richest = communityMember.getMoney();
            }
            if (communityMember.getMoney() < poorest) {
                poorest = communityMember.getMoney();
            }
        }
        System.out.println("After year " + year);
        System.out.println("The person with the most money has: " + richest);
        System.out.println("The person with the least money has: " + poorest);
    }


    public void simulateYears(int numYears) {
        // Simulate the lottery (see steps below)
        for (int year=0; year < numYears; year++) {
            // TODO Add pocket change for all community members, whether or not they're playing.
            // TODO Re-compute the players who are playing the lottery in the current year.
            // TODO Simulate the lottery for those players.

            addPocketChange();
            addLotteryEarning();
            addMoneyOverTime();
            printRichestPoorest(year);

            // 4. Update everyone's money for that year.
            for (CommunityMember cm : this.communityMembers) {
                cm.updateMoneyEachYear();
            }
        }
    }

}
