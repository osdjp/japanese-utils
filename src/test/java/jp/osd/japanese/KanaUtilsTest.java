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
 * {@link KanaUtils} のユニットテスト・クラスです。
 * 
 * @author itoasuka
 */
public class KanaUtilsTest {
    /**
     * 新たにユニットテスト・オブジェクトを構築します。
     */
    public KanaUtilsTest() {
        // 何もしない
    }

    /**
     * {@link KanaUtils} がインスタンス化できないようになっているかをテストします。
     * 
     * @throws Exception
     *             想定外のエラーが発生した場合。
     */
    @Test
    public void KanaUtil() throws Exception {
        Constructor<KanaUtils> c = KanaUtils.class.getDeclaredConstructor();
        assertFalse(c.isAccessible());
        c.setAccessible(true);
        c.newInstance();
    }

    /**
     * {@link KanaUtils#toKatakana(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>ひらがなをカタカナに変換できる。</li>
     * <li>記号や漢字などに対しては何もせずそのまま返す。</li>
     * </ol>
     */
    @Test
    public void toKatakana() {
        // Test 1
        assertEquals(
                "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラルレロワヲンァィゥェォャュョヮガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ",
                KanaUtils
                        .toKatakana("あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ"));

        // Test 2
        assertEquals("〔亜", KanaUtils.toKatakana("〔亜"));
    }

    /**
     * {@link KanaUtils#toHiragana(String)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>カタカナをひらがなに変換できる。</li>
     * <li>記号や漢字などに対しては何もせずそのまま返す。</li>
     * </ol>
     */
    @Test
    public void toHiragana() {
        assertEquals(
                "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらるれろわをんぁぃぅぇぉゃゅょゎがぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽー",
                KanaUtils
                        .toHiragana("アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラルレロワヲンァィゥェォャュョヮガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポー"));
        
        assertEquals("〔亜", KanaUtils.toHiragana("〔亜"));
    }

    /**
     * {@link KanaUtils#isHalfWidthKana(char)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>半角カタカナに対して <code>true</code> を返す。</li>
     * <li>全角カタカナに対して <code>false</code> を返す。</li>
     * <li>全角英数字に対して <code>false</code> を返す。</li>
     * <li>半角英数字に対して <code>false</code> を返す。</li>
     * </ol>
     */
    @Test
    public void isHalfWidthKana() {
        assertTrue(KanaUtils.isHalfWidthKana('ｱ'));
        assertFalse(KanaUtils.isHalfWidthKana('ア'));
        assertFalse(KanaUtils.isHalfWidthKana('￥'));
        assertFalse(KanaUtils.isHalfWidthKana('a'));
    }

    /**
     * {@link KanaUtils#isHiragana(char)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>半角カタカナに対して <code>false</code> を返す。</li>
     * <li>全角カタカナに対して <code>false</code> を返す。</li>
     * <li>全角ひらがなに対して <code>true</code> を返す。</li>
     * <li>全角英数字に対して <code>false</code> を返す。</li>
     * <li>半角英数字に対して <code>false</code> を返す。</li>
     * </ol>
     */
    @Test
    public void isHiragana() {
        assertFalse(KanaUtils.isHiragana('ｱ'));
        assertFalse(KanaUtils.isHiragana('ア'));
        assertTrue(KanaUtils.isHiragana('あ'));
        assertFalse(KanaUtils.isHiragana('￥'));
        assertFalse(KanaUtils.isHiragana('a'));
    }

    /**
     * {@link KanaUtils#getVowel(char)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>あ段の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Vowel#A}</code> を返す。</li>
     * <li>あ段の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#A}</code> を返す。</li>
     * <li>あ段の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#A}</code> を返す。</li>
     * <li>い段の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Vowel#I}</code> を返す。</li>
     * <li>い段の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#I}</code> を返す。</li>
     * <li>い段の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#I}</code> を返す。</li>
     * <li>う段の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Vowel#U}</code> を返す。</li>
     * <li>う段の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#U}</code> を返す。</li>
     * <li>う段の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#U}</code> を返す。</li>
     * <li>え段の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Vowel#E}</code> を返す。</li>
     * <li>え段の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#E}</code> を返す。</li>
     * <li>え段の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#E}</code> を返す。</li>
     * <li>お段の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Vowel#O}</code> を返す。</li>
     * <li>お段の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#O}</code> を返す。</li>
     * <li>お段の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Vowel#O}</code> を返す。</li>
     * <li>全角の仮名以外の文字に対して <code>null</code> を返す。</li>
     * <li>半角の仮名以外の文字に対して <code>null</code> を返す。</li>
     * </ol>
     */
    @Test
    public void getVowel() {
        assertEquals(Vowel.A, KanaUtils.getVowel('あ'));
        assertEquals(Vowel.A, KanaUtils.getVowel('カ'));
        assertEquals(Vowel.A, KanaUtils.getVowel('ｻ'));

        assertEquals(Vowel.I, KanaUtils.getVowel('ち'));
        assertEquals(Vowel.I, KanaUtils.getVowel('ニ'));
        assertEquals(Vowel.I, KanaUtils.getVowel('ﾋ'));

        assertEquals(Vowel.U, KanaUtils.getVowel('む'));
        assertEquals(Vowel.U, KanaUtils.getVowel('ル'));
        assertEquals(Vowel.U, KanaUtils.getVowel('ｳ'));

        assertEquals(Vowel.E, KanaUtils.getVowel('け'));
        assertEquals(Vowel.E, KanaUtils.getVowel('セ'));
        assertEquals(Vowel.E, KanaUtils.getVowel('ﾃ'));

        assertEquals(Vowel.O, KanaUtils.getVowel('の'));
        assertEquals(Vowel.O, KanaUtils.getVowel('ホ'));
        assertEquals(Vowel.O, KanaUtils.getVowel('ﾓ'));

        assertNull(KanaUtils.getVowel('Ａ'));
        assertNull(KanaUtils.getVowel('-'));
    }

    /**
     * {@link KanaUtils#getConsonant(char)} のユニット・テストです。以下の項目についてテストします。
     * <p>
     * <ol>
     * <li>ア行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#NONE}</code>
     * を返す。</li>
     * <li>ア行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#NONE}</code>
     * を返す。</li>
     * <li>ア行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#NONE}</code>
     * を返す。</li>
     * <li>カ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#K}</code> を返す。</li>
     * <li>カ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#K}</code> を返す。</li>
     * <li>カ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#K}</code> を返す。</li>
     * <li>ガ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#G}</code> を返す。</li>
     * <li>ガ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#G}</code> を返す。</li>
     * <li>サ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#S}</code> を返す。</li>
     * <li>サ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#S}</code> を返す。</li>
     * <li>サ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#S}</code> を返す。</li>
     * <li>ザ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#Z}</code> を返す。</li>
     * <li>ザ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#Z}</code> を返す。</li>
     * <li>タ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#T}</code> を返す。</li>
     * <li>タ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#T}</code> を返す。</li>
     * <li>タ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#T}</code> を返す。</li>
     * <li>ダ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#D}</code> を返す。</li>
     * <li>ダ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#D}</code> を返す。</li>
     * <li>ナ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#N}</code> を返す。</li>
     * <li>ナ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#N}</code> を返す。</li>
     * <li>ナ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#N}</code> を返す。</li>
     * <li>ハ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#H}</code> を返す。</li>
     * <li>ハ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#H}</code> を返す。</li>
     * <li>ハ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#H}</code> を返す。</li>
     * <li>バ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#B}</code> を返す。</li>
     * <li>バ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#B}</code> を返す。</li>
     * <li>パ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#P}</code> を返す。</li>
     * <li>パ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#P}</code> を返す。</li>
     * <li>マ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#M}</code> を返す。</li>
     * <li>マ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#M}</code> を返す。</li>
     * <li>マ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#M}</code> を返す。</li>
     * <li>ヤ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#Y}</code> を返す。</li>
     * <li>ヤ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#Y}</code> を返す。</li>
     * <li>ヤ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#Y}</code> を返す。</li>
     * <li>ワ行の全角ひらがなに対して
     * <code>{@link jp.osd.japanese.Consonant#W}</code> を返す。</li>
     * <li>ワ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#W}</code> を返す。</li>
     * <li>ワ行の半角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#W}</code> を返す。</li>
     * <li>ヴァ行の全角カタカナに対して
     * <code>{@link jp.osd.japanese.Consonant#V}</code> を返す。</li>
     * <li>全角の仮名以外の文字に対して <code>null</code> を返す。</li>
     * <li>半角の仮名以外の文字に対して <code>null</code> を返す。</li>
     * </ol>
     */
    @Test
    public void getConsonant() {
        assertEquals(Consonant.NONE, KanaUtils.getConsonant('あ'));
        assertEquals(Consonant.NONE, KanaUtils.getConsonant('イ'));
        assertEquals(Consonant.NONE, KanaUtils.getConsonant('ｳ'));

        assertEquals(Consonant.K, KanaUtils.getConsonant('け'));
        assertEquals(Consonant.K, KanaUtils.getConsonant('コ'));
        assertEquals(Consonant.K, KanaUtils.getConsonant('ｶ'));

        assertEquals(Consonant.G, KanaUtils.getConsonant('ぎ'));
        assertEquals(Consonant.G, KanaUtils.getConsonant('グ'));

        assertEquals(Consonant.S, KanaUtils.getConsonant('せ'));
        assertEquals(Consonant.S, KanaUtils.getConsonant('ソ'));
        assertEquals(Consonant.S, KanaUtils.getConsonant('ｻ'));

        assertEquals(Consonant.Z, KanaUtils.getConsonant('じ'));
        assertEquals(Consonant.Z, KanaUtils.getConsonant('ズ'));

        assertEquals(Consonant.T, KanaUtils.getConsonant('て'));
        assertEquals(Consonant.T, KanaUtils.getConsonant('ト'));
        assertEquals(Consonant.T, KanaUtils.getConsonant('ﾀ'));

        assertEquals(Consonant.D, KanaUtils.getConsonant('ぢ'));
        assertEquals(Consonant.D, KanaUtils.getConsonant('ヅ'));

        assertEquals(Consonant.N, KanaUtils.getConsonant('ね'));
        assertEquals(Consonant.N, KanaUtils.getConsonant('ノ'));
        assertEquals(Consonant.N, KanaUtils.getConsonant('ﾅ'));

        assertEquals(Consonant.H, KanaUtils.getConsonant('ひ'));
        assertEquals(Consonant.H, KanaUtils.getConsonant('フ'));
        assertEquals(Consonant.H, KanaUtils.getConsonant('ﾍ'));

        assertEquals(Consonant.B, KanaUtils.getConsonant('ぼ'));
        assertEquals(Consonant.B, KanaUtils.getConsonant('バ'));

        assertEquals(Consonant.P, KanaUtils.getConsonant('ぴ'));
        assertEquals(Consonant.P, KanaUtils.getConsonant('プ'));

        assertEquals(Consonant.M, KanaUtils.getConsonant('め'));
        assertEquals(Consonant.M, KanaUtils.getConsonant('モ'));
        assertEquals(Consonant.M, KanaUtils.getConsonant('ﾏ'));

        assertEquals(Consonant.Y, KanaUtils.getConsonant('ゆ'));
        assertEquals(Consonant.Y, KanaUtils.getConsonant('ヨ'));
        assertEquals(Consonant.Y, KanaUtils.getConsonant('ﾔ'));

        assertEquals(Consonant.R, KanaUtils.getConsonant('り'));
        assertEquals(Consonant.R, KanaUtils.getConsonant('ル'));
        assertEquals(Consonant.R, KanaUtils.getConsonant('ﾚ'));

        assertEquals(Consonant.W, KanaUtils.getConsonant('わ'));
        assertEquals(Consonant.W, KanaUtils.getConsonant('ヲ'));
        assertEquals(Consonant.W, KanaUtils.getConsonant('ﾜ'));

        assertEquals(Consonant.V, KanaUtils.getConsonant('ヴ'));

        assertNull(KanaUtils.getConsonant('Ａ'));
        assertNull(KanaUtils.getConsonant('-'));
    }
}
