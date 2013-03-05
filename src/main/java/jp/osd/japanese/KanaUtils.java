/*
 * Copyright 2013 OSD and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.osd.japanese;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 仮名に関するユーティリティクラスです。
 * 
 * @author asuka
 */
public class KanaUtils {
    private static final String[] VOWEL_INDENTIFIER;
    private static final String[] CONSONANT_IDENTIFIER;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle(
                "jp.osd.japanese.strings", Locale.JAPANESE);
        List<String> list = new ArrayList<String>();
        for (Vowel vowel : Vowel.values()) {
            String v = bundle.getString("vowel."
                    + vowel.toString().toLowerCase());
            list.add(v);
        }
        VOWEL_INDENTIFIER = new String[list.size()];
        list.toArray(VOWEL_INDENTIFIER);

        list = new ArrayList<String>();
        for (Consonant consonant : Consonant.values()) {
            String v = bundle.getString("consonant."
                    + consonant.toString().toLowerCase());
            list.add(v);
        }
        CONSONANT_IDENTIFIER = new String[list.size()];
        list.toArray(CONSONANT_IDENTIFIER);
    }

    /**
     * ユーティリティクラスであるため何もしません。
     */
    private KanaUtils() {
        // 何もしない
    }

    /**
     * ひらがなをカタカナに変換します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toKatakana(String src) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);

            if ('\u3040' <= c && c <= '\u309F') {
                sb.append((char) (c - '\u3040' + '\u30A0'));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * カタカナをひらがなに変換します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toHiragana(String src) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);

            if ('\u30A0' <= c && c <= '\u30F3') {
                sb.append((char) (c - '\u30A0' + '\u3040'));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 指定した文字が半角カナかを取得します。
     * 
     * @param c
     *            半角カナかを調べる文字
     * @return 半角カナのとき <code>true</code>
     * @since 0.1.1
     */
    public static boolean isHalfWidthKana(char c) {
        return '\uff61' <= c && c <= '\uff9f';
    }

    /**
     * 指定した文字がひらがなかを取得します。
     * 
     * @param c
     *            ひらがなかを調べる文字
     * @return ひらがなのとき <code>true</code>
     * @since 0.1.2
     */
    public static boolean isHiragana(char c) {
        return '\u3040' <= c && c <= '\u309F';
    }

    /**
     * 指定したひらがなまたはカタカナの母音を取得します。
     * 
     * @param c
     *            ひらがなまたはカタカナ
     * @return 母音を表す列挙オブジェクト
     * @since 0.2.0
     */
    public static Vowel getVowel(char c) {
        for (int i = 0; i < VOWEL_INDENTIFIER.length; i++) {
            if (-1 < VOWEL_INDENTIFIER[i].indexOf(c)) {
                return Vowel.values()[i];
            }
        }

        return null;
    }

    /**
     * 指定したひらがなまたはカタカナの子音を取得します。
     * 
     * @param c
     *            ひらがなまたはカタカナ
     * @return 子音を表す列挙オブジェクト
     * @since 0.2.0
     */
    public static Consonant getConsonant(char c) {
        for (int i = 0; i < CONSONANT_IDENTIFIER.length; i++) {
            if (-1 < CONSONANT_IDENTIFIER[i].indexOf(c)) {
                return Consonant.values()[i];
            }
        }

        return null;
    }
}
