 com.workspace.vish96esh.game1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int a[][] = new int[5][5];
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int i, j;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++)
                a[i][j] = -1;
        }
    }

    public boolean playable(int x, int y, int turn) {
        int i, j, x1, x2, y1, y2;
        int k = 0;
        if (x == 0) {
            x1 = x;
            x2 = x + 1;
        } else if (x == 4) {
            x1 = x - 1;
            x2 = x;
        } else {
            x1 = x - 1;
            x2 = x + 1;
        }

        if (y == 0) {
            y1 = y;
            y2 = y + 1;
        } else if (y == 4) {
            y1 = y - 1;
            y2 = y;
        } else {
            y1 = y - 1;
            y2 = y + 1;
        }
        switch (turn) {
            case 1:
                for (i = x1; i <= x2; i++) {
                    for (j = y1; j <= y2; j++) {
                        if (i == x && y == j)
                            continue;
                        if (a[i][j] == 2) {
                            k++;
                            return false;
                        }
                    }
                }
                if (k == 0)
                    return true;
                break;
            case 2:
                for (i = x1; i <= x2; i++) {
                    for (j = y1; j <= y2; j++) {
                        if (i == x && y == j)
                            continue;
                        if (a[i][j] == 1) {
                            k++;
                            return false;
                        }
                    }
                }
                if (k == 0)
                    return true;
                break;
        }
        return false;
    }

    public boolean gameOver() {
        int i, j;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                if (a[i][j]==-1 && playable(i, j, flag))
                    return false;
            }
        }
        return true;
    }

    public void check(View v) {
        //Button b = (Button) findViewById(v.getId());
        Button b = (Button) v;
        String id = getResources().getResourceEntryName(v.getId());
        Log.i("INFO", id);
        int x = Integer.parseInt(id.charAt(1) + "");
        int y = Integer.parseInt(id.charAt(2) + "");
        if (a[x][y] == -1 && playable(x, y, flag)) {
            switch (flag) {
                case 1:
                    Log.i("INFO", "X");
                    b.setText("X");
                    a[x][y] = 1;
                    flag = 2;
                    break;
                case 2:
                    Log.i("INFO", "O");
                    b.setText("O");
                    a[x][y] = 2;
                    flag = 1;
                    break;
            }
            if (gameOver()) {
                String str = "";
                switch (flag) {
                    case 1:
                        str = "Player 2 wins";
                        break;
                    case 2:
                        str = "Player 1 wins";
                        break;
                }
                Toast.makeText(this, str, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void newGame(View v) {
        flag = 1;
        int i, j;
        String buttonId = "b";
        int id;
        Button b;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                a[i][j] = -1;
                buttonId = buttonId + "" + i + "" + j;
                Log.i("NEW", buttonId);
                id = getResources().getIdentifier(buttonId, "id", getPackageName());
                b = (Button) findViewById(id);
                b.setText("");
                buttonId = "b";
            }
        }
        //b=(Button)findViewById(R.id.b_new);
        //b.setVisibility(View.INVISIBLE);
    }

}
