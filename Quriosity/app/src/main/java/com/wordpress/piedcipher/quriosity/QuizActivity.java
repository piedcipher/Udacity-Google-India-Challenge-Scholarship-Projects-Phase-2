package com.wordpress.piedcipher.quriosity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Resources mResources;
    private int mScore;
    private boolean mIsQuizOver = false;
    private final int ALERT_QUIZ_ABOUT = 0;

    private int mCorrectAnswerColor;
    private int mCorrectAnswerColorLight;
    private int mIncorrectAnswerColor;
    private int mIncorrectAnswerColorLight;

    private TextView mQuestion1;
    private TextView mQuestion1Fact;
    private RadioGroup mAnswer1Options;
    private RadioButton mAnswer1Option1;
    private RadioButton mAnswer1Option2;
    private RadioButton mAnswer1Option3;
    private RadioButton mAnswer1Option4;

    private TextView mQuestion2;
    private TextView mQuestion2Fact;
    private EditText mAnswer2;

    private TextView mQuestion3;
    private TextView mQuestion3Fact;
    private RadioGroup mAnswer3Options;
    private RadioButton mAnswer3Option1;
    private RadioButton mAnswer3Option2;

    private TextView mQuestion4;
    private TextView mQuestion4Fact;
    private CheckBox mAnswer4Option1;
    private CheckBox mAnswer4Option2;
    private CheckBox mAnswer4Option3;
    private CheckBox mAnswer4Option4;

    private TextView mQuestion5;
    private TextView mQuestion5Fact;
    private EditText mAnswer5;

    private TextView mQuestion6;
    private TextView mQuestion6Fact;
    private CheckBox mAnswer6Option1;
    private CheckBox mAnswer6Option2;
    private CheckBox mAnswer6Option3;
    private CheckBox mAnswer6Option4;

    private Button mSubmitButton;

    private int[][] mDrawableRights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        dataMemberInitializer();
        if (savedInstanceState == null) {
            quizAlertDialogBuilder(ALERT_QUIZ_ABOUT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("mIsQuizOver", mIsQuizOver);
        outState.putInt("mSubmitButton", mSubmitButton.getVisibility());

        outState.putBoolean("mAnswer1Option1", mAnswer1Option1.isEnabled());
        outState.putBoolean("mAnswer1Option2", mAnswer1Option2.isEnabled());
        outState.putBoolean("mAnswer1Option3", mAnswer1Option3.isEnabled());
        outState.putBoolean("mAnswer1Option4", mAnswer1Option4.isEnabled());
        outState.putBoolean("mAnswer2", mAnswer2.isEnabled());
        outState.putBoolean("mAnswer2Focus", mAnswer2.isFocusable());
        outState.putBoolean("mAnswer3Option1", mAnswer3Option1.isEnabled());
        outState.putBoolean("mAnswer3Option2", mAnswer3Option2.isEnabled());
        outState.putBoolean("mAnswer4Option1", mAnswer4Option1.isEnabled());
        outState.putBoolean("mAnswer4Option2", mAnswer4Option2.isEnabled());
        outState.putBoolean("mAnswer4Option3", mAnswer4Option3.isEnabled());
        outState.putBoolean("mAnswer4Option4", mAnswer4Option4.isEnabled());
        outState.putBoolean("mAnswer5", mAnswer5.isEnabled());
        outState.putBoolean("mAnswer5Focus", mAnswer5.isFocusable());
        outState.putBoolean("mAnswer6Option1", mAnswer6Option1.isEnabled());
        outState.putBoolean("mAnswer6Option2", mAnswer6Option2.isEnabled());
        outState.putBoolean("mAnswer6Option3", mAnswer6Option3.isEnabled());
        outState.putBoolean("mAnswer6Option4", mAnswer6Option4.isEnabled());

        outState.putInt("mQuestion1TextColor", mQuestion1.getCurrentTextColor());
        outState.putInt("mQuestion1Background", ((ColorDrawable) mQuestion1.getBackground()).getColor());
        outState.putInt("mQuestion2TextColor", mQuestion2.getCurrentTextColor());
        outState.putInt("mQuestion2Background", ((ColorDrawable) mQuestion2.getBackground()).getColor());
        outState.putInt("mQuestion3TextColor", mQuestion3.getCurrentTextColor());
        outState.putInt("mQuestion3Background", ((ColorDrawable) mQuestion3.getBackground()).getColor());
        outState.putInt("mQuestion4TextColor", mQuestion4.getCurrentTextColor());
        outState.putInt("mQuestion4Background", ((ColorDrawable) mQuestion4.getBackground()).getColor());
        outState.putInt("mQuestion5TextColor", mQuestion5.getCurrentTextColor());
        outState.putInt("mQuestion5Background", ((ColorDrawable) mQuestion5.getBackground()).getColor());
        outState.putInt("mQuestion6TextColor", mQuestion6.getCurrentTextColor());
        outState.putInt("mQuestion6Background", ((ColorDrawable) mQuestion6.getBackground()).getColor());

        outState.putInt("mAnswer1Option1Img", mDrawableRights[0][0]);
        outState.putInt("mAnswer1Option2Img", mDrawableRights[0][1]);
        outState.putInt("mAnswer1Option3Img", mDrawableRights[0][2]);
        outState.putInt("mAnswer1Option4Img", mDrawableRights[0][3]);
        outState.putInt("mAnswer2Img", mDrawableRights[1][0]);
        outState.putInt("mAnswer3Option1Img", mDrawableRights[2][0]);
        outState.putInt("mAnswer3Option2Img", mDrawableRights[2][1]);
        outState.putInt("mAnswer4Option1Img", mDrawableRights[3][0]);
        outState.putInt("mAnswer4Option2Img", mDrawableRights[3][1]);
        outState.putInt("mAnswer4Option3Img", mDrawableRights[3][2]);
        outState.putInt("mAnswer4Option4Img", mDrawableRights[3][3]);
        outState.putInt("mAnswer5Img", mDrawableRights[4][0]);
        outState.putInt("mAnswer6Option1Img", mDrawableRights[5][0]);
        outState.putInt("mAnswer6Option2Img", mDrawableRights[5][1]);
        outState.putInt("mAnswer6Option3Img", mDrawableRights[5][2]);
        outState.putInt("mAnswer6Option4Img", mDrawableRights[5][3]);

        outState.putInt("mQuestion1Fact", mQuestion1Fact.getVisibility());
        outState.putInt("mQuestion2Fact", mQuestion2Fact.getVisibility());
        outState.putInt("mQuestion3Fact", mQuestion3Fact.getVisibility());
        outState.putInt("mQuestion4Fact", mQuestion4Fact.getVisibility());
        outState.putInt("mQuestion5Fact", mQuestion5Fact.getVisibility());
        outState.putInt("mQuestion6Fact", mQuestion6Fact.getVisibility());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mIsQuizOver = savedInstanceState.getBoolean("mIsQuizOver");
        mSubmitButton.setVisibility(savedInstanceState.getInt("mSubmitButton"));

        mDrawableRights[0][0] = savedInstanceState.getInt("mAnswer1Option1Img");
        mDrawableRights[0][1] = savedInstanceState.getInt("mAnswer1Option2Img");
        mDrawableRights[0][2] = savedInstanceState.getInt("mAnswer1Option3Img");
        mDrawableRights[0][3] = savedInstanceState.getInt("mAnswer1Option4Img");
        mDrawableRights[1][0] = savedInstanceState.getInt("mAnswer2Img");
        mDrawableRights[2][0] = savedInstanceState.getInt("mAnswer3Option1Img");
        mDrawableRights[2][1] = savedInstanceState.getInt("mAnswer3Option2Img");
        mDrawableRights[3][0] = savedInstanceState.getInt("mAnswer4Option1Img");
        mDrawableRights[3][1] = savedInstanceState.getInt("mAnswer4Option2Img");
        mDrawableRights[3][2] = savedInstanceState.getInt("mAnswer4Option3Img");
        mDrawableRights[3][3] = savedInstanceState.getInt("mAnswer4Option4Img");
        mDrawableRights[4][0] = savedInstanceState.getInt("mAnswer5Img");
        mDrawableRights[5][0] = savedInstanceState.getInt("mAnswer6Option1Img");
        mDrawableRights[5][1] = savedInstanceState.getInt("mAnswer6Option2Img");
        mDrawableRights[5][2] = savedInstanceState.getInt("mAnswer6Option3Img");
        mDrawableRights[5][3] = savedInstanceState.getInt("mAnswer6Option4Img");

        mAnswer1Option1.setEnabled(savedInstanceState.getBoolean("mAnswer1Option1"));
        mAnswer1Option2.setEnabled(savedInstanceState.getBoolean("mAnswer1Option2"));
        mAnswer1Option3.setEnabled(savedInstanceState.getBoolean("mAnswer1Option3"));
        mAnswer1Option4.setEnabled(savedInstanceState.getBoolean("mAnswer1Option4"));
        mAnswer2.setEnabled(savedInstanceState.getBoolean("mAnswer2"));
        mAnswer2.setFocusable(savedInstanceState.getBoolean("mAnswer2Focus"));
        mAnswer3Option1.setEnabled(savedInstanceState.getBoolean("mAnswer3Option1"));
        mAnswer3Option2.setEnabled(savedInstanceState.getBoolean("mAnswer3Option2"));
        mAnswer4Option1.setEnabled(savedInstanceState.getBoolean("mAnswer4Option1"));
        mAnswer4Option2.setEnabled(savedInstanceState.getBoolean("mAnswer4Option2"));
        mAnswer4Option3.setEnabled(savedInstanceState.getBoolean("mAnswer4Option3"));
        mAnswer4Option4.setEnabled(savedInstanceState.getBoolean("mAnswer4Option4"));
        mAnswer5.setEnabled(savedInstanceState.getBoolean("mAnswer5"));
        mAnswer5.setFocusable(savedInstanceState.getBoolean("mAnswer5Focus"));
        mAnswer6Option1.setEnabled(savedInstanceState.getBoolean("mAnswer6Option1"));
        mAnswer6Option2.setEnabled(savedInstanceState.getBoolean("mAnswer6Option2"));
        mAnswer6Option3.setEnabled(savedInstanceState.getBoolean("mAnswer6Option3"));
        mAnswer6Option4.setEnabled(savedInstanceState.getBoolean("mAnswer6Option4"));

        mQuestion1.setTextColor(savedInstanceState.getInt("mQuestion1TextColor"));
        mQuestion1.setBackgroundColor(savedInstanceState.getInt("mQuestion1Background"));
        mQuestion2.setTextColor(savedInstanceState.getInt("mQuestion2TextColor"));
        mQuestion2.setBackgroundColor(savedInstanceState.getInt("mQuestion2Background"));
        mQuestion3.setTextColor(savedInstanceState.getInt("mQuestion3TextColor"));
        mQuestion3.setBackgroundColor(savedInstanceState.getInt("mQuestion3Background"));
        mQuestion4.setTextColor(savedInstanceState.getInt("mQuestion4TextColor"));
        mQuestion4.setBackgroundColor(savedInstanceState.getInt("mQuestion4Background"));
        mQuestion5.setTextColor(savedInstanceState.getInt("mQuestion5TextColor"));
        mQuestion5.setBackgroundColor(savedInstanceState.getInt("mQuestion5Background"));
        mQuestion6.setTextColor(savedInstanceState.getInt("mQuestion6TextColor"));
        mQuestion6.setBackgroundColor(savedInstanceState.getInt("mQuestion6Background"));

        mAnswer1Option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer1Option1Img"), 0);
        mAnswer1Option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer1Option2Img"), 0);
        mAnswer1Option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer1Option3Img"), 0);
        mAnswer1Option4.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer1Option4Img"), 0);
        mAnswer2.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer2Img"), 0);
        mAnswer3Option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer3Option1Img"), 0);
        mAnswer3Option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer3Option2Img"), 0);
        mAnswer4Option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer4Option1Img"), 0);
        mAnswer4Option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer4Option2Img"), 0);
        mAnswer4Option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer4Option3Img"), 0);
        mAnswer4Option4.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer4Option4Img"), 0);
        mAnswer5.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer5Img"), 0);
        mAnswer6Option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer6Option1Img"), 0);
        mAnswer6Option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer6Option2Img"), 0);
        mAnswer6Option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer6Option3Img"), 0);
        mAnswer6Option4.setCompoundDrawablesWithIntrinsicBounds(0, 0, savedInstanceState.getInt("mAnswer6Option4Img"), 0);

        mQuestion1Fact.setVisibility(savedInstanceState.getInt("mQuestion1Fact"));
        mQuestion2Fact.setVisibility(savedInstanceState.getInt("mQuestion2Fact"));
        mQuestion3Fact.setVisibility(savedInstanceState.getInt("mQuestion3Fact"));
        mQuestion4Fact.setVisibility(savedInstanceState.getInt("mQuestion4Fact"));
        mQuestion5Fact.setVisibility(savedInstanceState.getInt("mQuestion5Fact"));
        mQuestion6Fact.setVisibility(savedInstanceState.getInt("mQuestion6Fact"));
    }

    /**
     * This method initializes all the data-members of QuizActivity Class.
     */
    private void dataMemberInitializer() {
        mScore = 0;

        mDrawableRights = new int[6][];
        mDrawableRights[0] = new int[4];
        mDrawableRights[1] = new int[1];
        mDrawableRights[2] = new int[2];
        mDrawableRights[3] = new int[4];
        mDrawableRights[4] = new int[1];
        mDrawableRights[5] = new int[4];

        mResources = getResources();

        mCorrectAnswerColor = mResources.getColor(R.color.colorCorrectAnswer);
        mCorrectAnswerColorLight = mResources.getColor(R.color.colorCorrectAnswerLight);
        mIncorrectAnswerColor = mResources.getColor(R.color.colorIncorrectAnswer);
        mIncorrectAnswerColorLight = mResources.getColor(R.color.colorIncorrectAnswerLight);

        mQuestion1 = findViewById(R.id.question_1_textview);
        mQuestion1Fact = findViewById(R.id.question_1_fact);
        mAnswer1Options = findViewById(R.id.question_1_options);
        mAnswer1Option1 = findViewById(R.id.question_1_option_1_radiobutton);
        mAnswer1Option2 = findViewById(R.id.question_1_option_2_radiobutton);
        mAnswer1Option3 = findViewById(R.id.question_1_option_3_radiobutton);
        mAnswer1Option4 = findViewById(R.id.question_1_option_4_radiobutton);

        mQuestion2 = findViewById(R.id.question_2_textview);
        mQuestion2Fact = findViewById(R.id.question_2_fact);
        mAnswer2 = findViewById(R.id.question_2_edittext);

        mQuestion3 = findViewById(R.id.question_3_textview);
        mQuestion3Fact = findViewById(R.id.question_3_fact);
        mAnswer3Options = findViewById(R.id.question_3_options);
        mAnswer3Option1 = findViewById(R.id.question_3_option_1_radiobutton);
        mAnswer3Option2 = findViewById(R.id.question_3_option_2_radiobutton);

        mQuestion4 = findViewById(R.id.question_4_textview);
        mQuestion4Fact = findViewById(R.id.question_4_fact);
        mAnswer4Option1 = findViewById(R.id.question_4_option_1_checkbox);
        mAnswer4Option2 = findViewById(R.id.question_4_option_2_checkbox);
        mAnswer4Option3 = findViewById(R.id.question_4_option_3_checkbox);
        mAnswer4Option4 = findViewById(R.id.question_4_option_4_checkbox);

        mQuestion5 = findViewById(R.id.question_5_textview);
        mQuestion5Fact = findViewById(R.id.question_5_fact);
        mAnswer5 = findViewById(R.id.question_5_edittext);

        mQuestion6 = findViewById(R.id.question_6_textview);
        mQuestion6Fact = findViewById(R.id.question_6_fact);
        mAnswer6Option1 = findViewById(R.id.question_6_option_1_checkbox);
        mAnswer6Option2 = findViewById(R.id.question_6_option_2_checkbox);
        mAnswer6Option3 = findViewById(R.id.question_6_option_3_checkbox);
        mAnswer6Option4 = findViewById(R.id.question_6_option_4_checkbox);

        mSubmitButton = findViewById(R.id.submit_button);
    }

    /**
     * @param view is Submit Button.
     *             <p>
     *             This methods evaluates quiz by checking necessary conditions.
     */
    public void quizEvaluator(View view) {
        mScore = 0;

        if (mAnswer1Options.getCheckedRadioButtonId() == -1
                || mAnswer2.getText().toString().isEmpty()
                || mAnswer3Options.getCheckedRadioButtonId() == -1
                || (!mAnswer4Option1.isChecked() && !mAnswer4Option2.isChecked() && !mAnswer4Option3.isChecked() && !mAnswer4Option4.isChecked())
                || mAnswer5.getText().toString().isEmpty()
                || (!mAnswer6Option1.isChecked() && !mAnswer6Option2.isChecked() && !mAnswer6Option3.isChecked() && !mAnswer6Option4.isChecked())) {
            Toast.makeText(QuizActivity.this, mResources.getString(R.string.toast_response_0), Toast.LENGTH_SHORT).show();
        } else {
            if (mAnswer1Options.getCheckedRadioButtonId() == R.id.question_1_option_2_radiobutton) {
                mScore++;
                mQuestion1.setTextColor(mCorrectAnswerColor);
                mQuestion1.setBackgroundColor(mCorrectAnswerColorLight);
            } else {
                mQuestion1.setTextColor(mIncorrectAnswerColor);
                mQuestion1.setBackgroundColor(mIncorrectAnswerColorLight);
            }

            mAnswer1Option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.close, 0);
            mAnswer1Option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mAnswer1Option3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.close, 0);
            mAnswer1Option4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.close, 0);
            mQuestion1Fact.setVisibility(View.VISIBLE);

            mDrawableRights[0][0] = R.drawable.close;
            mDrawableRights[0][1] = R.drawable.check;
            mDrawableRights[0][2] = R.drawable.close;
            mDrawableRights[0][3] = R.drawable.close;

            if (mAnswer2.getText().toString().equalsIgnoreCase(mResources.getString(R.string.question_2_answer))) {
                mScore++;
                mQuestion2.setTextColor(mCorrectAnswerColor);
                mQuestion2.setBackgroundColor(mCorrectAnswerColorLight);
                mAnswer2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
                mDrawableRights[1][0] = R.drawable.check;
            } else {
                mQuestion2.setTextColor(mIncorrectAnswerColor);
                mQuestion2.setBackgroundColor(mIncorrectAnswerColorLight);
                mAnswer2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.close, 0);
                mDrawableRights[1][0] = R.drawable.close;
            }

            mQuestion2Fact.setVisibility(View.VISIBLE);

            if (mAnswer3Options.getCheckedRadioButtonId() == R.id.question_3_option_2_radiobutton) {
                mScore++;
                mQuestion3.setTextColor(mCorrectAnswerColor);
                mQuestion3.setBackgroundColor(mCorrectAnswerColorLight);
            } else {
                mQuestion3.setTextColor(mIncorrectAnswerColor);
                mQuestion3.setBackgroundColor(mIncorrectAnswerColorLight);
            }

            mAnswer3Option1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.close, 0);
            mAnswer3Option2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mQuestion3Fact.setVisibility(View.VISIBLE);

            mDrawableRights[2][0] = R.drawable.close;
            mDrawableRights[2][1] = R.drawable.check;

            if (mAnswer4Option1.isChecked()
                    && mAnswer4Option2.isChecked()
                    && !mAnswer4Option3.isChecked()
                    && mAnswer4Option4.isChecked()) {
                mScore++;
                mQuestion4.setTextColor(mCorrectAnswerColor);
                mQuestion4.setBackgroundColor(mCorrectAnswerColorLight);
            } else {
                mQuestion4.setTextColor(mIncorrectAnswerColor);
                mQuestion4.setBackgroundColor(mIncorrectAnswerColorLight);
            }

            mAnswer4Option1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mAnswer4Option2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mAnswer4Option3.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.close, 0);
            mAnswer4Option4.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mQuestion4Fact.setVisibility(View.VISIBLE);

            mDrawableRights[3][0] = R.drawable.check;
            mDrawableRights[3][1] = R.drawable.check;
            mDrawableRights[3][2] = R.drawable.close;
            mDrawableRights[3][3] = R.drawable.check;

            if (mAnswer5.getText().toString().equalsIgnoreCase(mResources.getString(R.string.question_5_answer))
                    || mAnswer5.getText().toString().equalsIgnoreCase(mResources.getString(R.string.question_5_answer_2))) {
                mScore++;
                mQuestion5.setTextColor(mCorrectAnswerColor);
                mQuestion5.setBackgroundColor(mCorrectAnswerColorLight);
                mAnswer5.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
                mDrawableRights[4][0] = R.drawable.check;
            } else {
                mQuestion5.setTextColor(mIncorrectAnswerColor);
                mQuestion5.setBackgroundColor(mIncorrectAnswerColorLight);
                mAnswer5.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.close, 0);
                mDrawableRights[4][0] = R.drawable.close;
            }

            mQuestion5Fact.setVisibility(View.VISIBLE);

            if (mAnswer6Option1.isChecked()
                    && mAnswer6Option2.isChecked()
                    && !mAnswer6Option3.isChecked()
                    && mAnswer6Option4.isChecked()) {
                mScore++;
                mQuestion6.setTextColor(mCorrectAnswerColor);
                mQuestion6.setBackgroundColor(mCorrectAnswerColorLight);
            } else {
                mQuestion6.setTextColor(mIncorrectAnswerColor);
                mQuestion6.setBackgroundColor(mIncorrectAnswerColorLight);
            }

            mAnswer6Option1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mAnswer6Option2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mAnswer6Option3.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.close, 0);
            mAnswer6Option4.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            mQuestion6Fact.setVisibility(View.VISIBLE);

            mDrawableRights[5][0] = R.drawable.check;
            mDrawableRights[5][1] = R.drawable.check;
            mDrawableRights[5][2] = R.drawable.close;
            mDrawableRights[5][3] = R.drawable.check;

            disableViews();

            Toast.makeText(QuizActivity.this, "You scored " + String.valueOf(mScore + "/" + 6), Toast.LENGTH_LONG).show();
            mScore = 0;
        }
    }

    /**
     * This method disables RadioButtons, Checkboxes and Button.
     */
    private void disableViews() {
        mAnswer1Option1.setEnabled(false);
        mAnswer1Option2.setEnabled(false);
        mAnswer1Option3.setEnabled(false);
        mAnswer1Option4.setEnabled(false);

        mAnswer2.setEnabled(false);
        mAnswer2.setFocusable(false);

        mAnswer3Option1.setEnabled(false);
        mAnswer3Option2.setEnabled(false);

        mAnswer4Option1.setEnabled(false);
        mAnswer4Option2.setEnabled(false);
        mAnswer4Option3.setEnabled(false);
        mAnswer4Option4.setEnabled(false);

        mAnswer5.setEnabled(false);
        mAnswer5.setFocusable(false);

        mAnswer6Option1.setEnabled(false);
        mAnswer6Option2.setEnabled(false);
        mAnswer6Option3.setEnabled(false);
        mAnswer6Option4.setEnabled(false);

        mSubmitButton.setVisibility(View.INVISIBLE);
        mIsQuizOver = true;
    }

    /**
     * @param alertFor determines type of alert which is requested.
     * @return an AlertDialog.
     * <p>
     * This method creates an AlertDialog on-demand.
     */
    private AlertDialog quizAlertDialogBuilder(int alertFor) {
        AlertDialog.Builder quizAlertBuilder;
        quizAlertBuilder = new AlertDialog.Builder(QuizActivity.this).setCancelable(false);

        if (alertFor == ALERT_QUIZ_ABOUT) {
            quizAlertBuilder.setMessage(mResources.getString(R.string.alert_response_0_message));
            quizAlertBuilder.setPositiveButton(mResources.getString(R.string.alert_response_0_positive_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        }

        return quizAlertBuilder.create();
    }
}