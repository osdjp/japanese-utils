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


/**
 * 全角半角変換を行うユーティリティ・クラスです。
 * 
 * @author asuka
 */
public final class WidthUtils {
    /**
     * 半角カナの変換テーブルです。
     */
    private static final String[] HALF_WIDTH_KANA = { "\uff61", "\uff62",
            "\uff63", "\uff64", "\uff65", "\uff66", "\uff67", "\uff68",
            "\uff69", "\uff6a", "\uff6b", "\uff6c", "\uff6d", "\uff6e",
            "\uff6f", "\uff70", "\uff71", "\uff72", "\uff73", "\uff74",
            "\uff75", "\uff76", "\uff77", "\uff78", "\uff79", "\uff7a",
            "\uff7b", "\uff7c", "\uff7d", "\uff7e", "\uff7f", "\uff80",
            "\uff81", "\uff82", "\uff83", "\uff84", "\uff85", "\uff86",
            "\uff87", "\uff88", "\uff89", "\uff8a", "\uff8b", "\uff8c",
            "\uff8d", "\uff8e", "\uff8f", "\uff90", "\uff91", "\uff92",
            "\uff93", "\uff94", "\uff95", "\uff96", "\uff97", "\uff98",
            "\uff99", "\uff9a", "\uff9b", "\uff9c", "\uff9d", "\uff9e",
            "\uff9f", "\uff73\uff9e", "\uff76\uff9e", "\uff77\uff9e",
            "\uff78\uff9e", "\uff79\uff9e", "\uff7a\uff9e", "\uff7b\uff9e",
            "\uff7c\uff9e", "\uff7d\uff9e", "\uff7e\uff9e", "\uff7f\uff9e",
            "\uff80\uff9e", "\uff81\uff9e", "\uff82\uff9e", "\uff83\uff9e",
            "\uff84\uff9e", "\uff8a\uff9e", "\uff8b\uff9e", "\uff8c\uff9e",
            "\uff8d\uff9e", "\uff8e\uff9e", "\uff8a\uff9f", "\uff8b\uff9f",
            "\uff8c\uff9f", "\uff8d\uff9f", "\uff8e\uff9f", "\uff9c\uff9e",
            "\uff66\uff9e" };

    /**
     * 全角カナの変換テーブルです。
     */
    private static final char[] FULL_WIDTH_KANA = { '\u3002', '\u300c',
            '\u300d', '\u3001', '\u30fb', '\u30f2', '\u30a1', '\u30a3',
            '\u30a5', '\u30a7', '\u30a9', '\u30e3', '\u30e5', '\u30e7',
            '\u30c3', '\u30fc', '\u30a2', '\u30a4', '\u30a6', '\u30a8',
            '\u30aa', '\u30ab', '\u30ad', '\u30af', '\u30b1', '\u30b3',
            '\u30b5', '\u30b7', '\u30b9', '\u30bb', '\u30bd', '\u30bf',
            '\u30c1', '\u30c4', '\u30c6', '\u30c8', '\u30ca', '\u30cb',
            '\u30cc', '\u30cd', '\u30ce', '\u30cf', '\u30d2', '\u30d5',
            '\u30d8', '\u30db', '\u30de', '\u30df', '\u30e0', '\u30e1',
            '\u30e2', '\u30e4', '\u30e6', '\u30e8', '\u30e9', '\u30ea',
            '\u30eb', '\u30ec', '\u30ed', '\u30ef', '\u30f3', '\u309b',
            '\u309c', '\u30f4', '\u30ac', '\u30ae', '\u30b0', '\u30b2',
            '\u30b4', '\u30b6', '\u30b8', '\u30ba', '\u30bc', '\u30be',
            '\u30c0', '\u30c2', '\u30c5', '\u30c7', '\u30c9', '\u30d0',
            '\u30d3', '\u30d6', '\u30d9', '\u30dc', '\u30d1', '\u30d4',
            '\u30d7', '\u30da', '\u30dd', '\u30f7', '\u30fa' };

    /**
     * 半角 ASCII の補足変換テーブルです。
     */
    private static final char[] HALF_WIDTH_ASCII = { '\\', '"', '\'' };

    /**
     * 全角 ASCII の補足変換テーブルです。
     */
    private static final char[] FULL_WIDTH_ASCII = { '\uffe5', '\u201d',
            '\u2019' };

    /**
     * ユーティリティクラスであるため何もしません。
     */
    private WidthUtils() {
        // 何もしない
    }

    /**
     * 全角カタカナと字種が ASCII 文字に対応する全角文字を半角に変換します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toHalfWidth(String src) {
        String ret = toHalfWidthKana(toHalfWidthAscii(src));

        return ret.replaceAll("\u3000", " ");
    }

    /**
     * 字種が空白を除く ASCII 文字に対応する全角文字を半角に変換します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toHalfWidthAscii(String src) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);

            sb.append(toHalfWidthAsciiChar(c));
        }

        return sb.toString();
    }

    /**
     * 全角カタカナを半角カタカナに変換します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toHalfWidthKana(String src) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);

            sb.append(toHalfWidthKanaChar(c));
        }

        return sb.toString();
    }

    /**
     * 半角カタカナと ASCII 文字を全角に変換します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toFullWidth(String src) {
        String ret = toFullWidthKana(toFullWidthAscii(src));

        return ret.replaceAll(" ", "\u3000");
    }

    /**
     * 空白を除く ASCII 文字を全角に変換します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toFullWidthAscii(String src) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);

            sb.append(toFullWidthAsciiChar(c));
        }

        return sb.toString();
    }

    /**
     * 半角カタカナを全角カタカナに変換します。 単純に 1 対 1
     * で変換するのではなく、濁点や半濁点を考慮します。全角カタカナに変換できない場合は、引数で指定した文字列を返します。
     * 
     * @param src
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    public static String toFullWidthKana(String src) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            StringBuilder tmp = new StringBuilder();

            tmp.append(c);

            // 濁点・半濁点がつく可能性があるか？
            if (i < src.length() - 1
                    && ('\uff73' == c || ('\uff76' <= c && c <= '\uff8e')
                            || '\uff9c' == c || '\uff66' == c)) {
                if (src.charAt(i + 1) == '\uff9e') {
                    // 濁点がついている場合
                    tmp.append('\uff9e');
                    i++;
                } else if ('\uff8a' <= c && c <= '\uff8e'
                        && src.charAt(i + 1) == '\uff9f') {
                    // 半濁点がついている場合
                    tmp.append('\uff9f');
                    i++;
                }
            }

            sb.append(toFullWidthKanaChar(tmp.toString()));
        }

        return sb.toString();
    }

    /**
     * 字種が空白を除く ASCII 文字に対応する全角文字を半角に変換します。
     * 
     * @param c
     *            変換対象の文字
     * @return 変換後の文字
     */
    private static char toHalfWidthAsciiChar(char c) {
        for (int i = 0; i < FULL_WIDTH_ASCII.length; i++) {
            if (c == FULL_WIDTH_ASCII[i]) {
                return HALF_WIDTH_ASCII[i];
            }
        }

        if ('\uff01' <= c && c <= '\uff5e') {
            return (char) (c - '\uff01' + '!');
        }

        return c;
    }

    /**
     * 全角カタカナを半角カタカナに変換します。 引数で指定した全角カタカナが濁音や半濁音であった場合、戻り値は濁点や半濁点を含む 2
     * 文字の半角カタカナになることに注意してください。半角カタカナに変換できない場合は、引数で指定した文字をそのまま文字列型に変換して出力します。
     * 
     * @param c
     *            変換対象の文字
     * @return 変換後の文字列
     */
    private static String toHalfWidthKanaChar(char c) {
        String ret = Character.toString(c);

        for (int i = 0; i < FULL_WIDTH_KANA.length; i++) {
            if (FULL_WIDTH_KANA[i] == c) {
                ret = HALF_WIDTH_KANA[i];
                break;
            }
        }

        return ret;
    }

    /**
     * 空白を除く ASCII 文字を全角に変換します。
     * 
     * @param c
     *            変換対象の文字
     * @return 変換後の文字
     */
    private static char toFullWidthAsciiChar(char c) {
        for (int i = 0; i < HALF_WIDTH_ASCII.length; i++) {
            if (c == HALF_WIDTH_ASCII[i]) {
                return FULL_WIDTH_ASCII[i];
            }
        }

        if ('!' <= c && c <= '~') {
            return (char) (c - '!' + '\uff01');
        }

        return c;
    }

    /**
     * 半角カタカナを全角カタカナに変換します。 単純に 1 対 1
     * で変換するのではなく、濁点や半濁点を考慮します。引数で指定する半角カタカナは全角カタカナ 1
     * 文字分に相当するものでなければなりません。全角カタカナに変換できない場合は、引数で指定した文字列を返します。
     * 
     * @param s
     *            変換対象の文字列
     * @return 変換後の文字列
     */
    private static String toFullWidthKanaChar(String s) {
        String ret = s;

        for (int i = 0; i < HALF_WIDTH_KANA.length; i++) {
            if (HALF_WIDTH_KANA[i].equals(s)) {
                ret = Character.toString(FULL_WIDTH_KANA[i]);
                break;
            }
        }
        return ret;
    }

    /**
     * 文字列の（理論上の）幅を取得します。これは、半角を 1 全角を 2 としたもので文字列をシフト JIS でエンコードした場合のバイト数と等価です。
     * 
     * @param src
     *            幅を求める対象の文字列
     * @return 幅
     */
    public static int width(String src) {
        int len = src.length();
        int blen = 0;
        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (isHalfWidth(c)) {
                blen++;
            } else {
                blen += 2;
            }
        }
        return blen;
    }

    /**
     * 指定した文字が半角文字であるかを取得します。
     * @param c 半角かを調べる文字
     * @return 半角のとき <code>true</code>
     * @since 0.3.0
     */
    public static boolean isHalfWidth(char c) {
        return c <= '\u00ff' || KanaUtils.isHalfWidthKana(c);
    }

    /**
     * 指定した文字列から（理論上の）幅の分だけ先頭から取得します。
     *
     * このメソッドは {@link String#substring(int, int)} の第 1 引数に 0 を指定したものに似ています。しかし、文字数ではなく幅（半角を 1 全角を 2 としたもの）を指定する点が異なっています。
     *
     * @param src 対象の文字列
     * @param width 取得する幅
     * @return 取得した部分文字列
     */
    public static String left(String src, int width) {
        StringBuilder sb = new StringBuilder();
        int len = src.length();
        int blen = 0;
        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (isHalfWidth(c)) {
                blen++;
            } else {
                blen += 2;
            }
            if (width < blen) {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
