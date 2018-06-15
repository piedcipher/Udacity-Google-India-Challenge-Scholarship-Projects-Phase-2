package com.wordpress.piedcipher.clashofcupheads;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private final int ALERT_GAME_RULES = -1;
    private final int ALERT_RESET_GAME = 0;
    private final int ALERT_WINNER = 1;

    private Resources mResources;

    private TextView mCupheadScoreTextView;
    private TextView mCupheadHpTextView;
    private TextView mMugmanScoreTextView;
    private TextView mMugmanHpTextView;

    private int mCupheadScore;
    private int mCupheadHp;
    private int mMugmanScore;
    private int mMugmanHp;

    private Button mCupheadShootButton;
    private Button mCupheadExmoveButton;
    private Button mMugmanShootButton;
    private Button mMugmanExmoveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameAlertDialogBuilder(ALERT_GAME_RULES).show();
        viewInitializer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_button:
                gameAlertDialogBuilder(ALERT_RESET_GAME).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mCupheadScore", mCupheadScore);
        outState.putInt("mCupheadHp", mCupheadHp);
        outState.putInt("mCupheadHpHightlightedColor", mCupheadHpTextView.getCurrentTextColor());
        outState.putInt("mMugmanScore", mMugmanScore);
        outState.putInt("mMugmanHp", mMugmanHp);
        outState.putInt("mMugmanHpHightlightedColor", mMugmanHpTextView.getCurrentTextColor());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCupheadScore = savedInstanceState.getInt("mCupheadScore");
        mCupheadHp = savedInstanceState.getInt("mCupheadHp");
        mMugmanScore = savedInstanceState.getInt("mMugmanScore");
        mMugmanHp = savedInstanceState.getInt("mMugmanHp");

        mCupheadScoreTextView.setText(String.valueOf(mCupheadScore));
        mCupheadHpTextView.setText(String.valueOf(mCupheadHp));
        mCupheadHpTextView.setTextColor(savedInstanceState.getInt("mCupheadHpHightlightedColor"));
        mMugmanScoreTextView.setText(String.valueOf(mMugmanScore));
        mMugmanHpTextView.setText(String.valueOf(mMugmanHp));
        mMugmanHpTextView.setTextColor(savedInstanceState.getInt("mMugmanHpHightlightedColor"));
    }

    /**
     * This method is called when onCreate method is called.
     * This method initialize all the views which are used in various parts of application.
     */
    private void viewInitializer() {
        mResources = getResources();

        mCupheadScoreTextView = findViewById(R.id.cuphead_score);
        mCupheadHpTextView = findViewById(R.id.cuphead_hp);
        mMugmanScoreTextView = findViewById(R.id.mugman_score);
        mMugmanHpTextView = findViewById(R.id.mugman_hp);

        mCupheadShootButton = findViewById(R.id.cuphead_shoot);
        mCupheadExmoveButton = findViewById(R.id.cuphead_exmove);
        mMugmanShootButton = findViewById(R.id.mugman_shoot);
        mMugmanExmoveButton = findViewById(R.id.mugman_exmove);
    }

    /**
     * This method is called when onCreate method is called.
     * This method initialize all the data-members which are used in various parts of application.
     */
    private void dataMemberInitializer() {
        mCupheadScore = mMugmanScore = Integer.parseInt(mResources.getString(R.string.initial_score));
        mCupheadHp = mMugmanHp = Integer.parseInt(mResources.getString(R.string.initial_hp));

        mCupheadScoreTextView.setText(String.valueOf(mCupheadScore));
        mCupheadHpTextView.setText(String.valueOf(mCupheadHp));
        mMugmanScoreTextView.setText(String.valueOf(mMugmanScore));
        mMugmanHpTextView.setText(String.valueOf(mMugmanHp));

        mCupheadScoreTextView.setTextColor(mResources.getColor(R.color._212121));
        mCupheadHpTextView.setTextColor(mResources.getColor(R.color.very_high_hp));
        mMugmanScoreTextView.setTextColor(mResources.getColor(R.color._212121));
        mMugmanHpTextView.setTextColor(mResources.getColor(R.color.very_high_hp));

        mCupheadShootButton.setClickable(true);
        mCupheadExmoveButton.setClickable(true);
        mMugmanShootButton.setClickable(true);
        mMugmanExmoveButton.setClickable(true);
    }

    /**
     * @param view This is the Shoot button for player Cuphead.
     *             <p>
     *             This method is called when Shoot button for Cuphead is clicked.
     *             This method gives Score-points to Cuphead and Decreases HP of Mugman.
     */
    public void cupheadShootAction(View view) {
        mCupheadScore++;
        mCupheadScoreTextView.setText(String.valueOf(mCupheadScore));
        mMugmanHp--;
        mMugmanHpTextView.setText(String.valueOf(mMugmanHp));
        liveScoreHighlighter();
        liveHpHighlighter(mMugmanHp, mMugmanHpTextView);
    }

    /**
     * @param view This is the Ex-move button for player Cuphead.
     *             <p>
     *             This method is called when Ex-move button for Cuphead is clicked.
     *             This method gives Score-points to Cuphead and Decreases HP of Mugman Significantly.
     */
    public void cupheadExmoveAction(View view) {
        mCupheadScore += 2;
        mCupheadScoreTextView.setText(String.valueOf(mCupheadScore));
        mMugmanHp--;
        mMugmanHpTextView.setText(String.valueOf(mMugmanHp));
        liveScoreHighlighter();
        liveHpHighlighter(mMugmanHp, mMugmanHpTextView);
    }

    /**
     * @param view This is the Shoot button for player Mugman.
     *             <p>
     *             This method is called when Shoot button for Mugman is clicked.
     *             This method gives Score-points to Mugman and Decreases HP of Cuphead.
     */
    public void mugmanShootAction(View view) {
        mMugmanScore++;
        mMugmanScoreTextView.setText(String.valueOf(mMugmanScore));
        mCupheadHp--;
        mCupheadHpTextView.setText(String.valueOf(mCupheadHp));
        liveScoreHighlighter();
        liveHpHighlighter(mCupheadHp, mCupheadHpTextView);
    }

    /**
     * @param view This is the Shoot button for player Mugman.
     *             <p>
     *             This method is called when Ex-move button for Mugman is clicked.
     *             This method gives Score-points to Mugman and Decreases HP of Cuphead Significantly.
     */
    public void mugmanExmoveAction(View view) {
        mMugmanScore += 2;
        mMugmanScoreTextView.setText(String.valueOf(mMugmanScore));
        mCupheadHp--;
        mCupheadHpTextView.setText(String.valueOf(mCupheadHp));
        liveScoreHighlighter();
        liveHpHighlighter(mCupheadHp, mCupheadHpTextView);
    }

    /**
     * This method is called every-time, score of either of the player is changed.
     * This method highlights score of a player,
     * ..with Green color for leading player.
     * ..with Red color for non-leading player.
     * ..with Black color when scores are equal.
     */
    private void liveScoreHighlighter() {
        if (mCupheadScore > mMugmanScore) {
            mCupheadScoreTextView.setTextColor(mResources.getColor(R.color.leading_player));
            mMugmanScoreTextView.setTextColor(mResources.getColor(R.color.non_leading_player));
        } else if (mMugmanScore > mCupheadScore) {
            mMugmanScoreTextView.setTextColor(mResources.getColor(R.color.leading_player));
            mCupheadScoreTextView.setTextColor(mResources.getColor(R.color.non_leading_player));
        } else {
            mCupheadScoreTextView.setTextColor(mResources.getColor(R.color._212121));
            mMugmanScoreTextView.setTextColor(mResources.getColor(R.color._212121));
        }
    }

    /**
     * @param playerHp         This is the current HP value of a player.
     * @param playerHpTextView This variable determines for player this method is called.
     *                         <p>
     *                         This method is called every-time, HP of either of the player is changed.
     *                         This method check HP level of player & highlights HP of a player,
     *                         ..with Green color when HP is between 20 & 25 (Very-High HP)
     *                         ..with LightGreen color when HP is between 15 & 19 (High HP)
     *                         ..with Lime color when HP is between 10 & 14 (Moderate HP)
     *                         ..with Orange color when HP is between 5 & 9 (Low HP)
     *                         ..with Red color when HP is between 1 & 4 (Very-Low HP)
     */
    private void liveHpHighlighter(double playerHp, TextView playerHpTextView) {
        if (mCupheadHp == 0 || mMugmanHp == 0) {
            winnerWinnerChickenDinner();
        } else {
            if (playerHp >= 20 && playerHp <= 25) {
                playerHpTextView.setTextColor(mResources.getColor(R.color.very_high_hp));
            } else if (playerHp >= 15 && playerHp < 20) {
                playerHpTextView.setTextColor(mResources.getColor(R.color.high_hp));
            } else if (playerHp >= 10 && playerHp < 15) {
                playerHpTextView.setTextColor(mResources.getColor(R.color.moderate_hp));
            } else if (playerHp >= 5 && playerHp < 10) {
                playerHpTextView.setTextColor(mResources.getColor(R.color.low_hp));
            } else if (playerHp > 1 && playerHp < 5) {
                playerHpTextView.setTextColor(mResources.getColor(R.color.very_low_hp));
            } else {
                playerHpTextView.setTextColor(mResources.getColor(R.color._212121));
            }
        }
    }

    /**
     * This method is called by liveHpHighlighter(double, TextView) when one of player's HP reaches to 0.
     * This method disables action button and shows an alert for Winner.
     */
    private void winnerWinnerChickenDinner() { // Method name, inspired by a video-game, "PUBG"
        mCupheadShootButton.setClickable(false);
        mCupheadExmoveButton.setClickable(false);
        mMugmanShootButton.setClickable(false);
        mMugmanExmoveButton.setClickable(false);

        if (mCupheadHp == 0) {
            Toast.makeText(GameActivity.this, "Mugman Won", Toast.LENGTH_SHORT).show();
        } else if(mMugmanHp == 0) {
            Toast.makeText(GameActivity.this, "Cuphead Won", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(GameActivity.this, "It's a Tie", Toast.LENGTH_SHORT).show();
        }

        gameAlertDialogBuilder(ALERT_WINNER).show();
    }

    /**
     * @param alertFor This parameter determines what kind of alert is requested.
     * @return This method returns an AlertDialog.
     *                 <p>
     *                 This method is called when onCreate method is called.
     *                 This method creates a basic structure for an alert on-demand, which is shown to user.
     */
    private AlertDialog gameAlertDialogBuilder(int alertFor) {
        final AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(GameActivity.this).setCancelable(false);

        if (alertFor == ALERT_RESET_GAME) {
            alertDialogBuilder.setMessage(R.string.alert_message_reset);
            alertDialogBuilder.setPositiveButton(R.string.alert_response_1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(GameActivity.this, "Reset-Game Completed", Toast.LENGTH_SHORT).show();
                    dataMemberInitializer(); // reset-game
                }
            });
            alertDialogBuilder.setNegativeButton(R.string.alert_response_0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        } else if (alertFor == ALERT_WINNER) {
            String winnerAlertMessage;
            if (mCupheadHp == 0) {
                winnerAlertMessage = mResources.getString(R.string.player_2) + " Won";
            } else if(mMugmanHp == 0) {
                winnerAlertMessage = mResources.getString(R.string.player_1) + " Won";
            } else {
                winnerAlertMessage = "It's a Tie!";
            }

            alertDialogBuilder.setMessage(winnerAlertMessage);
            alertDialogBuilder.setPositiveButton(R.string.alert_response_2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "New-Game Started", Toast.LENGTH_SHORT).show();
                    dataMemberInitializer(); // new-game
                }
            });
            alertDialogBuilder.setNegativeButton(R.string.alert_response_3, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(GameActivity.this, "Quitting..", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else if(alertFor == ALERT_GAME_RULES) {
            String gameRules = "Shoot gives 1 point & decreases HP of Opponent by 1.";
            gameRules += "\n\nEx-move gives 2 points & decreases HP of Opponent by 1.";
            gameRules += "\n\nWhen HP level reaches 0, that player loses game.";

            alertDialogBuilder.setMessage(gameRules);
            alertDialogBuilder.setPositiveButton(R.string.alert_response_4, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dataMemberInitializer();
                }
            });
        }

        return alertDialogBuilder.create();
    }
}