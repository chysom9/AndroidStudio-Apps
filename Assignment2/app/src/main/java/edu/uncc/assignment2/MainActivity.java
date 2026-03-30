package edu.uncc.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private int i ;
    private int round1 = 4;
    private int round2 = 4;
    private boolean lastRound = false;
    private final int[] openCases = {R.drawable.suitcase_open_1,
            R.drawable.suitcase_open_10,
            R.drawable.suitcase_open_50,
            R.drawable.suitcase_open_100,
            R.drawable.suitcase_open_300,
            R.drawable.suitcase_open_1000,
            R.drawable.suitcase_open_10000,
            R.drawable.suitcase_open_50000,
            R.drawable.suitcase_open_100000,
            R.drawable.suitcase_open_500000
    };

    private final int[] openRewards = {R.drawable.reward_open_1,
            R.drawable.reward_open_10,
            R.drawable.reward_open_50,
            R.drawable.reward_open_100,
            R.drawable.reward_open_300,
            R.drawable.reward_open_1000,
            R.drawable.reward_open_10000,
            R.drawable.reward_open_50000,
            R.drawable.reward_open_100000,
            R.drawable.reward_open_500000

    };
    private final int[] val = {0,1,2,3,4,5,6,7,8,9};

    Random rand = new Random();
    int randomIndex = rand.nextInt(openCases.length);


/////////////////////////////////////////START OF VIEW////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide button upon starting view
        Button deal = findViewById(R.id.deal);
        Button noDeal = findViewById(R.id.noDeal);
        TextView boxesleft = findViewById(R.id.caseRemain);
        deal.setVisibility(View.INVISIBLE);
        noDeal.setVisibility(View.INVISIBLE);

        ImageView case1 = findViewById(R.id.case1);
        ImageView case2 = findViewById(R.id.case2);
        ImageView case3 = findViewById(R.id.case3);
        ImageView case4 = findViewById(R.id.case4);
        ImageView case5 = findViewById(R.id.case5);
        ImageView case6 = findViewById(R.id.case6);
        ImageView case7 = findViewById(R.id.case7);
        ImageView case8 = findViewById(R.id.case8);
        ImageView case9 = findViewById(R.id.case9);
        ImageView case10 = findViewById(R.id.case10);

        //reward view

        ImageView[] cases = {
                case1,
                case2,
                case3,
                case4,
                case5,
                case6,
                case7,
                case8,
                case9,
                case10
        };

        ImageView reward1 = findViewById(R.id.reward1);
        ImageView reward2 = findViewById(R.id.reward2);
        ImageView reward3 = findViewById(R.id.reward3);
        ImageView reward4 = findViewById(R.id.reward4);
        ImageView reward5 = findViewById(R.id.reward5);
        ImageView reward6 = findViewById(R.id.reward6);
        ImageView reward7 = findViewById(R.id.reward7);
        ImageView reward8 = findViewById(R.id.reward8);
        ImageView reward9 = findViewById(R.id.reward9);
        ImageView reward10 = findViewById(R.id.reward10);

        ImageView[] rewards = {
                reward1,
                reward2,
                reward3,
                reward4,
                reward5,
                reward6,
                reward7,
                reward8,
                reward9,
                reward10};

        reward1.setTag("1");
        reward2.setTag("10");
        reward3.setTag("50");
        reward4.setTag("100");
        reward5.setTag("300");
        reward6.setTag("1000");
        reward7.setTag("10000");
        reward8.setTag("50000");
        reward9.setTag("100000");
        reward10.setTag("500000");

        Button reset = findViewById(R.id.reset);



        ArrayList<ImageView> rewardsClone = new ArrayList<>(Arrays.asList(rewards));

        ArrayList<Integer> clickedRandom = new ArrayList<>(9);

        HashSet<Integer> clickedIndex = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            final int index = i; // create a final variable to capture the value of i
            cases[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if (!clickedIndex.contains(index)) {

                        if (round1 != 0) {//4
                            while (clickedRandom.contains(openCases[randomIndex]) ) {
                                randomIndex = rand.nextInt(openCases.length);
                            }
                            cases[index].setImageResource(openCases[randomIndex]); // use the captured index variable
                            rewards[randomIndex].setImageResource(openRewards[randomIndex]);
                            rewardsClone.remove(rewards[randomIndex]);
                            clickedRandom.add(openCases[randomIndex]);


                            randomIndex = rand.nextInt(openCases.length);
                            clickedIndex.add(index);
                            round1--;
                            boxesleft.setText("Choose " + round1 + " Cases");
                        } else {//4
                            int total = 0;
                            for (ImageView i : rewardsClone) {
                                total += Integer.parseInt((rewardsClone.get(rewardsClone.indexOf(i)).getTag()).toString()) ;

                            }
                            boxesleft.setText("Bank Deal is $" + total/(10 - clickedRandom.size()));
                            deal.setVisibility(View.VISIBLE);
                            noDeal.setVisibility(View.VISIBLE);
                        }//1

                    }
                }
            });
        }

        deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                for (ImageView i : rewardsClone) {
                        total += Integer.parseInt((rewardsClone.get(rewardsClone.indexOf(i)).getTag()).toString()) ;

                }


                boxesleft.setText( "You Win $"+ Integer.toString((total)/ (10 - clickedRandom.size())));
                noDeal.setVisibility(View.INVISIBLE);
            }
        });
        noDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boxesleft.setText("Choose " + round2 + " Cases");
                for (int i = 0; i < 10; i++) {
                    final int index = i; // create a final variable to capture the value of i
                    cases[i].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {



                            if (!clickedIndex.contains(index)) {
                                if (round2 != 0) {
                                    while (clickedRandom.contains(openCases[randomIndex]) ) {
                                        randomIndex = rand.nextInt(openCases.length);
                                    }
                                    cases[index].setImageResource(openCases[randomIndex]); // use the captured index variable
                                    rewards[randomIndex].setImageResource(openRewards[randomIndex]);
                                    rewardsClone.remove(rewards[randomIndex]);
                                    clickedRandom.add(openCases[randomIndex]);


                                    randomIndex = rand.nextInt(openCases.length);
                                    clickedIndex.add(index);
                                    round2--;
                                    boxesleft.setText("Choose " + round2 + " Cases");


                                }


                                else {
                                    round2 = 1;
                                    int total = 0;
                                    for (ImageView i : rewardsClone) {
                                        total += Integer.parseInt((rewardsClone.get(rewardsClone.indexOf(i)).getTag()).toString()) ;

                                    }
                                    boxesleft.setText("Bank Deal is $" + total/(10 - clickedRandom.size()));
                                    deal.setVisibility(View.VISIBLE);
                                    noDeal.setVisibility(View.VISIBLE);
                                }

                            }

                        }
                    });
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your reset logic here
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });




    }

}