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
 * 子音を表す列挙型です。
 * 
 * @author asuka
 * @since 0.2.0
 */
public enum Consonant {
    /** 子音がない、すなわち 50 音表のア行にある仮名であることを表します。 */
    NONE,
    /** 子音が「K」、すなわち 50 音表のカ行にある仮名であることを表します。 */
    K,
    /** 子音が「G」、すなわち 50 音表のギ行にある仮名であることを表します。 */
    G,
    /** 子音が「S」、すなわち 50 音表のサ行にある仮名であることを表します。 */
    S,
    /** 子音が「Z」、すなわち 50 音表のザ行にある仮名であることを表します。 */
    Z,
    /** 子音が「T」、すなわち 50 音表のタ行にある仮名であることを表します。 */
    T,
    /** 子音が「D」、すなわち 50 音表のダ行にある仮名であることを表します。 */
    D,
    /** 子音が「N」、すなわち 50 音表のナ行にある仮名であることを表します。 */
    N,
    /** 子音が「H」、すなわち 50 音表のハ行にある仮名であることを表します。 */
    H,
    /** 子音が「B」、すなわち 50 音表のバ行にある仮名であることを表します。 */
    B,
    /** 子音が「P」、すなわち 50 音表のパ行にある仮名であることを表します。 */
    P,
    /** 子音が「M」、すなわち 50 音表のマ行にある仮名であることを表します。 */
    M,
    /** 子音が「Y」、すなわち 50 音表のヤ行にある仮名であることを表します。 */
    Y,
    /** 子音が「R」、すなわち 50 音表のラ行にある仮名であることを表します。 */
    R,
    /** 子音が「W」、すなわち 50 音表のワ行にある仮名であることを表します。 */
    W,
    /** 子音が「V」、すなわち 50 音表のヴァ行にある仮名であることを表します。 */
    V
}
