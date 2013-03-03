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

import static org.testng.Assert.*;

import java.lang.reflect.Constructor;

import org.testng.annotations.Test;

/**
 * {@link WidthUtils} のユニットテスト・クラスです。
 * 
 * @author itoasuka
 */
public class WidthUtilsTest {
    /**
     * 新たにユニットテスト・オブジェクトを構築します。
     */
    public WidthUtilsTest() {
        // 何もしない
    }

    /**
     * {@link WidthUtils} がインスタンス化できないようになっているかをテストします。
     * 
     * @throws Exception
     *             想定外のエラーが発生した場合。
     */
    @Test
    public void widthUtil() throws Exception {
        Constructor<WidthUtils> c = WidthUtils.class.getDeclaredConstructor();
        assertFalse(c.isAccessible());
        c.setAccessible(true);
        c.newInstance();
    }

    /**
     * {@link WidthUtils#toHalfWidth(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>全角の英小文字を半角の英小文字に変換できる。</li>
     * <li>全角の英大文字を半角の英大文字に変換できる。</li>
     * <li>全角の数字を半角の数字に変換できる。</li>
     * <li>半角で対応する ASCII 記号を持つ全角の記号を半角に変換できる。</li>
     * <li>濁点および半濁点がつかない全角カタカナを半角カタカナに変換できる。</li>
     * <li>濁点および半濁点がつく全角カタカナを半角カタカナに変換できる。</li>
     * <li>撥音などのための小さな全角カタカナと長音記号などの全角の日本語用記号を半角に変換できる。</li>
     * <li>全角のひらがなが入力された場合はそのまま返す。</li>
     * <li>半角文字が入力された場合はそのまま返す。</li>
     * </ol>
     */
    @Test
    public void toHalfWidth() {
        // Test 1
        assertEquals("abcdefghijklmnopqrstuvwxyz", WidthUtils
                .toHalfWidth("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ"));
        // Test 2
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", WidthUtils
                .toHalfWidth("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ"));
        // Test 3
        assertEquals("1234567890", WidthUtils.toHalfWidth("１２３４５６７８９０"));
        // Test 4
        assertEquals(" -^\\@[;:],./\\!\"#$%&'()=~|{+*}<>?_", WidthUtils
                .toHalfWidth("　－＾￥＠［；：］，．／￥！”＃＄％＆’（）＝～｜｛＋＊｝＜＞？＿"));
        // Test 5
        assertEquals(
                "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ",
                WidthUtils
                        .toHalfWidth("アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン"));
        // Test 6
        assertEquals(
                "ｳﾞｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾜﾞｦﾞ",
                WidthUtils.toHalfWidth("ヴガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポヷヺ"));
        // Test 7
        assertEquals("ｧｨｩｪｫｯｬｭｮｰ｢｣｡､", WidthUtils.toHalfWidth("ァィゥェォッャュョー「」。、"));
        // Test 8
        assertEquals(
                "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ",
                WidthUtils
                        .toHalfWidth("あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ"));
        // Test 9
        assertEquals(
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ",
                WidthUtils
                        .toHalfWidth("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ"));
    }

    /**
     * {@link WidthUtils#toHalfWidthAscii(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>全角の英小文字を半角の英小文字に変換できる。</li>
     * <li>全角の英大文字を半角の英大文字に変換できる。</li>
     * <li>全角の数字を半角の数字に変換できる。</li>
     * <li>半角で対応する ASCII 記号を持つ全角の記号を半角に変換できる。</li>
     * <li>ただし全角スペースは変換しない。</li>
     * <li>濁点および半濁点がつかない全角カタカナが入力された場合はそのまま返す。</li>
     * <li>濁点および半濁点がつく全角カタカナが入力された場合はそのまま返す。</li>
     * <li>撥音などのための小さな全角カタカナや長音記号などの全角の日本語用記号が入力された場合はそのまま返す。</li>
     * <li>全角のひらがなが入力された場合はそのまま返す。</li>
     * </ol>
     */
    @Test
    public void toHalfWidthAscii() {
        // Test 1
        assertEquals("abcdefghijklmnopqrstuvwxyz", WidthUtils
                .toHalfWidthAscii("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ"));
        // Test 2
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", WidthUtils
                .toHalfWidth("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ"));
        // Test 3
        assertEquals("1234567890", WidthUtils.toHalfWidthAscii("１２３４５６７８９０"));
        // Test 4
        assertEquals("-^\\@[;:],./\\!\"#$%&'()=~|{+*}<>?_", WidthUtils
                .toHalfWidthAscii("－＾￥＠［；：］，．／￥！”＃＄％＆’（）＝～｜｛＋＊｝＜＞？＿"));
        // Test 5
        assertEquals("　", WidthUtils.toHalfWidthAscii("　"));
        // Test 6
        assertEquals(
                "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン",
                WidthUtils
                        .toHalfWidthAscii("アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン"));
        // Test 7
        assertEquals("ヴガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポヷヺ", WidthUtils
                .toHalfWidthAscii("ヴガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポヷヺ"));
        // Test 8
        assertEquals("ァィゥェォッャュョー「」。、", WidthUtils
                .toHalfWidthAscii("ァィゥェォッャュョー「」。、"));
        // Test 9
        assertEquals(
                "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ",
                WidthUtils
                        .toHalfWidthAscii("あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ"));
    }

    /**
     * {@link WidthUtils#toHalfWidthKana(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>濁点および半濁点がつかない全角カタカナを半角カタカナに変換できる。</li>
     * <li>濁点および半濁点がつく全角カタカナを半角カタカナに変換できる。</li>
     * <li>撥音などのための小さな全角カタカナと長音記号などの全角の日本語用記号を半角に変換できる。</li>
     * <li>ただし全角スペースは変換しない。</li>
     * <li>全角のひらがなが入力された場合はそのまま返す。</li>
     * <li>全角の英小文字が入力された場合はそのまま返す。</li>
     * <li>全角の英大文字が入力された場合はそのまま返す。</li>
     * <li>全角の数字が入力された場合はそのまま返す。</li>
     * <li>半角で対応する ASCII 記号を持つ全角の記号が入力された場合はそのまま返す。</li>
     * </ol>
     */
    @Test
    public void toHalfWidthKana() {
        // Test 1
        assertEquals(
                "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ",
                WidthUtils
                        .toHalfWidthKana("アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン"));
        // Test 2
        assertEquals(
                "ｳﾞｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾜﾞｦﾞ",
                WidthUtils.toHalfWidthKana("ヴガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポヷヺ"));
        // Test 3
        assertEquals("ｧｨｩｪｫｯｬｭｮｰ｢｣｡､", WidthUtils.toHalfWidth("ァィゥェォッャュョー「」。、"));
        // Test 4
        assertEquals("　", WidthUtils.toHalfWidthKana("　"));
        // Test 5
        assertEquals(
                "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ",
                WidthUtils
                        .toHalfWidthKana("あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ"));
        // Test 6
        assertEquals("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ", WidthUtils
                .toHalfWidthKana("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ"));
        // Test 7
        assertEquals("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ", WidthUtils
                .toHalfWidthKana("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ"));
        // Test 8
        assertEquals("１２３４５６７８９０", WidthUtils.toHalfWidthKana("１２３４５６７８９０"));
        // Test 9
        assertEquals("－＾￥＠［；：］，．／￥！”＃＄％＆’（）＝～｜｛＋＊｝＜＞？＿", WidthUtils
                .toHalfWidthKana("－＾￥＠［；：］，．／￥！”＃＄％＆’（）＝～｜｛＋＊｝＜＞？＿"));
    }

    /**
     * {@link WidthUtils#toFullWidth(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>半角の英小文字を全角の英小文字に変換できる。</li>
     * <li>半角の英大文字を全角の英大文字に変換できる。</li>
     * <li>半角の数字を全角の数字に変換できる。</li>
     * <li>ASCII 記号を全角に変換できる。</li>
     * <li>濁点および半濁点がつかない半角カタカナを全角カタカナに変換できる。</li>
     * <li>濁点および半濁点がつく半角カタカナを全角カタカナに変換できる。</li>
     * <li>撥音などのための小さな半角カタカナと長音記号などの全角の日本語用記号を全角に変換できる。</li>
     * </ol>
     */
    @Test
    public void toFullWidth() {
        // Test 1
        assertEquals("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ", WidthUtils
                .toFullWidth("abcdefghijklmnopqrstuvwxyz"));
        // Test 2
        assertEquals("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ", WidthUtils
                .toFullWidth("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        // Test 3
        assertEquals("１２３４５６７８９０", WidthUtils.toFullWidth("1234567890"));
        // Test 4
        assertEquals("　－＾￥＠［；：］，．／￥！”＃＄％＆’（）＝～｜｛＋＊｝＜＞？＿", WidthUtils
                .toFullWidth(" -^\\@[;:],./\\!\"#$%&'()=~|{+*}<>?_"));
        // Test 5
        assertEquals(
                "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン",
                WidthUtils
                        .toFullWidth("ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ"));
        // Test 6
        assertEquals(
                "ヴガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポヷヺ",
                WidthUtils
                        .toFullWidth("ｳﾞｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾜﾞｦﾞ"));
        // Test 7
        assertEquals("ァィゥェォッャュョー「」。、", WidthUtils.toFullWidth("ｧｨｩｪｫｯｬｭｮｰ｢｣｡､"));
    }

    /**
     * {@link WidthUtils#toFullWidthAscii(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>半角の英小文字を全角の英小文字に変換できる。</li>
     * <li>半角の英大文字を全角の英大文字に変換できる。</li>
     * <li>半角の数字を全角の数字に変換できる。</li>
     * <li>ASCII 記号を全角に変換できる。</li>
     * <li>ただし半角スペースは変換しない。</li>
     * <li>濁点および半濁点がつかない半角カタカナが入力された場合はそのまま返す。</li>
     * <li>濁点および半濁点がつく半角カタカナが入力された場合はそのまま返す。</li>
     * <li>撥音などのための小さな半角カタカナと長音記号などが入力された場合はそのまま返す。</li>
     * </ol>
     */
    @Test
    public void toFullWidthAscii() {
        // Test 1
        assertEquals("ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ", WidthUtils
                .toFullWidthAscii("abcdefghijklmnopqrstuvwxyz"));
        // Test 2
        assertEquals("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ", WidthUtils
                .toFullWidthAscii("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        // Test 3
        assertEquals("１２３４５６７８９０", WidthUtils.toFullWidthAscii("1234567890"));
        // Test 4
        assertEquals("－＾￥＠［；：］，．／￥！”＃＄％＆’（）＝～｜｛＋＊｝＜＞？＿", WidthUtils
                .toFullWidthAscii("-^\\@[;:],./\\!\"#$%&'()=~|{+*}<>?_"));
        // Test 5
        assertEquals(WidthUtils.toFullWidthAscii(" "), " ");
        // Test 6
        assertEquals(
                "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ",
                WidthUtils
                        .toFullWidthAscii("ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ"));
        // Test 7
        assertEquals(
                "ｳﾞｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾜﾞｦﾞ",
                WidthUtils
                        .toFullWidthAscii("ｳﾞｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾜﾞｦﾞ"));
        // Test 8
        assertEquals("ｧｨｩｪｫｯｬｭｮｰ｢｣｡､", WidthUtils
                .toFullWidthAscii("ｧｨｩｪｫｯｬｭｮｰ｢｣｡､"));
    }

    /**
     * {@link WidthUtils#toFullWidthKana(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>濁点および半濁点がつかない半角カタカナを全角カタカナに変換できる。</li>
     * <li>濁点および半濁点がつく半角カタカナを全角カタカナに変換できる。</li>
     * <li>撥音などのための小さな半角カタカナと長音記号などの全角の日本語用記号を全角に変換できる。</li>
     * <li>ただし半角スペースは変換しない。</li>
     * <li>半角の英小文字が入力された場合はそのまま返す。</li>
     * <li>半角の英大文字が入力された場合はそのまま返す。</li>
     * <li>半角の数字が入力された場合はそのまま返す。</li>
     * <li>ASCII 記号が入力された場合はそのまま返す。</li>
     * </ol>
     */
    @Test
    public void toFullWidthKana() {
        // Test 1
        assertEquals(
                "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン",
                WidthUtils
                        .toFullWidthKana("ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ"));
        // Test 2
        assertEquals(
                "ヴガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポヷヺ",
                WidthUtils
                        .toFullWidthKana("ｳﾞｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟﾜﾞｦﾞ"));
        // Test 3
        assertEquals("ァィゥェォッャュョー", WidthUtils.toFullWidthKana("ｧｨｩｪｫｯｬｭｮｰ"));
        // Test 4
        assertEquals(" ", WidthUtils.toFullWidthKana(" "));
        // Test 5
        assertEquals("abcdefghijklmnopqrstuvwxyz", WidthUtils
                .toFullWidthKana("abcdefghijklmnopqrstuvwxyz"));
        // Test 6
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", WidthUtils
                .toFullWidthKana("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        // Test 7
        assertEquals("1234567890", WidthUtils.toFullWidthKana("1234567890"));
        // Test 8
        assertEquals("-^\\@[;:],./\\!\"#$%&'()=~|{+*}<>?_", WidthUtils
                .toFullWidthKana("-^\\@[;:],./\\!\"#$%&'()=~|{+*}<>?_"));

    }

    /**
     * {@link WidthUtils#width(String)} のユニット・テストです。以下の項目についてテストします。
     * <ol>
     * <li>全角半角混合の文字列の幅を正しく取得できる。</li>
     * <li>半角のみの文字列の幅を正しく取得できる。</li>
     * <li>全角のみの文字列の幅を正しく取得できる。</li>
     * </ol>
     */
    @Test
    public void width() {
        // Test 1
        assertEquals(11, WidthUtils.width("一23四56七ｱ"));

        // Test 2
        assertEquals(10, WidthUtils.width("1234567890"));

        // Test 3
        assertEquals(12, WidthUtils.width("一二三四五ア"));
    }

    /**
     * {@link WidthUtils#isHalfWidth(char)} のユニット・テストです。以下の項目についてテストします。
     * <ol>
     * <li>半角英字を正しく判定できる。</li>
     * <li>半角数字を正しく判定できる。</li>
     * <li>半角カナを正しく判定できる。</li>
     * <li>全角英字を正しく判定できる。</li>
     * <li>全角数字を正しく判定できる。</li>
     * <li>全角かなを正しく判定できる。</li>
     * <li>全角カナを正しく判定できる。</li>
     * <li>全角漢字を正しく判定できる。</li>
     * </ol>
     *
     * @since 0.3.0
     */
    @Test
    public void isHalfWidth() {
        // Test 1
        assertTrue(WidthUtils.isHalfWidth('a'));
        // Test 2
        assertTrue(WidthUtils.isHalfWidth('1'));
        // Test 3
        assertTrue(WidthUtils.isHalfWidth('ｱ'));
        // Test 4
        assertFalse(WidthUtils.isHalfWidth('ａ'));
        // Test 5
        assertFalse(WidthUtils.isHalfWidth('１'));
        // Test 6
        assertFalse(WidthUtils.isHalfWidth('あ'));
        // Test 7
        assertFalse(WidthUtils.isHalfWidth('ア'));
        // Test 8
        assertFalse(WidthUtils.isHalfWidth('亜'));
    }

    /**
     * {@link WidthUtils#left(String, int)} のユニット・テストです。以下の項目についてテストします。
     * <ol>
     * <li>全角半角混合の文字列から正しく取得できる。</li>
     * <li>半角のみの文字列から正しく取得できる。</li>
     * <li>全角のみの文字列から正しく取得できる。</li>
     * </ol>
     *
     * @since 0.3.0
     */
    @Test
    public void left() {
        // Test 1
        assertEquals("一二34", WidthUtils.left("一二34五", 6));
        assertEquals("一二34", WidthUtils.left("一二34五", 7));
        assertEquals("一二34五", WidthUtils.left("一二34五", 10));
        // Test 2
        assertEquals("12ab", WidthUtils.left("12ab34cd", 4));
        assertEquals("12ab34cd", WidthUtils.left("12ab34cd", 10));
        // Test 3
        assertEquals("一二", WidthUtils.left("一二三四", 4));
        assertEquals("一二", WidthUtils.left("一二三四", 5));
        assertEquals("一二三四", WidthUtils.left("一二三四", 10));
    }
}
