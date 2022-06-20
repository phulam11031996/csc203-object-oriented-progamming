import java.util.HashSet;
import java.util.Random;

public class Game {
    private HashSet<Integer> winningNumbers;
    Random random = new Random();

    public void winningLotNumber() {
        HashSet fiveRandom = new HashSet(5);
        do {
            int number = random.nextInt(43);
            if (!fiveRandom.contains(number))
                fiveRandom.add(number);
        }while(fiveRandom.size() != 5);

        this.winningNumbers = fiveRandom;
    }

    public int matchesNumber(HashSet playerNumbers) {
        int numberOfMatch = 0;
        for (int winningNumber : this.winningNumbers){
            if (playerNumbers.contains(winningNumber))
                numberOfMatch += 1;
        }
        return numberOfMatch;
    }

    public float play(HashSet lotteryNumbers) {
        this.winningLotNumber();
        int numberOfMatches = this.matchesNumber(lotteryNumbers);
        if (numberOfMatches == 2)
            return 1f;
        if (numberOfMatches == 3)
            return 10.86f;
        if (numberOfMatches == 4)
            return 197.53f;
        if (numberOfMatches == 4)
            return 212534.83f;
        return -1f;
    }



}

