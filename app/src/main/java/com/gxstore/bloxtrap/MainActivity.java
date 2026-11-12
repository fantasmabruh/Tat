package com.gxstore.bloxtrap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public final class MainActivity extends Activity {
    private static final int BG = Color.rgb(9, 9, 11);
    private static final int CARD = Color.rgb(24, 24, 27);
    private static final int PURPLE = Color.rgb(167, 139, 250);
    private SharedPreferences prefs;

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        prefs = getSharedPreferences("gx_bloxtrap", MODE_PRIVATE);
        render();
    }

    private TextView label(String value, int size, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        if (bold) view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return view;
    }

    private GradientDrawable rounded(int color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(color);
        shape.setCornerRadius(dp(16));
        return shape;
    }

    private void render() {
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(BG);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(20), dp(24), dp(20), dp(24));
        scroll.addView(root);

        TextView brand = label("GX STORE  /  MOBILE", 12, PURPLE, true);
        brand.setLetterSpacing(0.12f);
        root.addView(brand);

        TextView title = label("GX BloxTrap", 30, Color.WHITE, true);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        TextView subtitle = label("ROBLOX COMPANION  •  ANDROID 11+", 11, 0xFF9CA3AF, true);
        LinearLayout.LayoutParams subtitleParams = new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(5);
        root.addView(subtitle, subtitleParams);

        addCard(root, "LAUNCHER", "Abra o Roblox oficial instalado no aparelho.",
                "ABRIR ROBLOX", v -> openRoblox());
        addCard(root, "PERSONALIZADOR", "Escolha e salve um perfil visual local.",
                "ESCOLHER PERFIL", v -> chooseProfile());
        addCard(root, "OTIMIZADOR", "Consulte recomendações seguras para jogar melhor.",
                "VER RECOMENDAÇÕES", v -> showOptimization());
        addCard(root, "FASTFLAGS", "Veja informações e limitações das FastFlags no Android.",
                "VER INFORMAÇÕES", v -> showFastFlags());

        TextView footer = label("GX STORE  •  BUILD WITHOUT LIMITS", 10, 0xFF71717A, true);
        footer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams footerParams = new LinearLayout.LayoutParams(-1, -2);
        footerParams.topMargin = dp(26);
        root.addView(footer, footerParams);
        setContentView(scroll);
    }

    private void addCard(LinearLayout parent, String heading, String description,
                         String buttonText, View.OnClickListener action) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(16), dp(16), dp(16));
        card.setBackground(rounded(CARD));
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2);
        cardParams.topMargin = dp(14);
        parent.addView(card, cardParams);

        TextView h = label(heading, 12, PURPLE, true);
        h.setLetterSpacing(0.08f);
        card.addView(h);

        TextView d = label(description, 14, 0xFFE4E4E7, false);
        LinearLayout.LayoutParams dParams = new LinearLayout.LayoutParams(-1, -2);
        dParams.topMargin = dp(8);
        card.addView(d, dParams);

        Button button = new Button(this);
        button.setText(buttonText);
        button.setTextColor(Color.WHITE);
        button.setAllCaps(false);
        button.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(Color.rgb(109, 40, 217)));
        LinearLayout.LayoutParams bParams = new LinearLayout.LayoutParams(-1, dp(48));
        bParams.topMargin = dp(12);
        card.addView(button, bParams);
        button.setOnClickListener(action);
    }

    private void openRoblox() {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.roblox.client");
        if (intent == null) {
            new AlertDialog.Builder(this).setTitle("Roblox não encontrado")
                    .setMessage("Instale o Roblox pela Google Play Store e tente novamente.")
                    .setPositiveButton("OK", null).show();
            return;
        }
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível abrir o Roblox.", Toast.LENGTH_LONG).show();
        }
    }

    private void chooseProfile() {
        final String[] profiles = {"Black Premium", "Dark Minimal", "Purple Accent"};
        String current = prefs.getString("profile", profiles[0]);
        int checked = 0;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].equals(current)) checked = i;
        }
        new AlertDialog.Builder(this).setTitle("Perfil visual")
                .setSingleChoiceItems(profiles, checked, (dialog, which) -> {
                    prefs.edit().putString("profile", profiles[which]).apply();
                    Toast.makeText(this, "Perfil salvo: " + profiles[which],
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }).setNegativeButton("Fechar", null).show();
    }

    private void showOptimization() {
        new AlertDialog.Builder(this).setTitle("Otimização segura")
                .setMessage("• Feche aplicativos que não estiver usando.\n"
                        + "• Mantenha o Roblox atualizado.\n"
                        + "• Prefira uma conexão Wi-Fi estável.\n"
                        + "• Evite jogar com o aparelho superaquecido.\n\n"
                        + "Este app não altera processos, memória ou arquivos internos do Roblox.")
                .setPositiveButton("Entendi", null).show();
    }

    private void showFastFlags() {
        new AlertDialog.Builder(this).setTitle("FastFlags no Android")
                .setMessage("FastFlags são configurações internas do cliente Roblox. "
                        + "Um aplicativo externo comum não pode garantir que sejam aplicadas. "
                        + "Esta versão fornece informações e não injeta código nem modifica "
                        + "o cliente Roblox.")
                .setPositiveButton("OK", null).show();
    }
  }
