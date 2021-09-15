package com.example.rail.shared.enum

import kotlin.reflect.KClass

/**
 * 指定された Enumの、指定された条件に合致する列挙子を返却する．
 *
 * @param E 対象のEnum
 * @param where 条件
 *
 * @return 条件に一致した列挙子
 */
inline fun <reified E : Enum<*>> findEnum(where: (value: E) -> Boolean): E {

    val candidates = E::class.java.enumConstants
            .filter { where(it) }

    when {
        candidates.isEmpty()   -> throw IllegalArgumentException("指定された条件に該当する ${E::class} の列挙子はみつかりません．")
        candidates.count() > 1 -> throw IllegalArgumentException("指定された条件に該当する ${E::class} の列挙子が複数みつかりました．")
    }

    return candidates.first()
}

//TIP: こちらのシグネチャーの場合、KClass<E> を引数として指定する必要がある  e.g. `findEnum2(Sign::class) { it.signum == signum }`
//     ↑の`findEnum`のように具象型パラメータ（inline にして reified でマーク）にすることで、呼び出しを簡単にできる  e.g. `findEnum { it.signum == signum }`
fun <E : Enum<*>> findEnum2(clazz: KClass<E>, where: (value: E) -> Boolean): E {

    val candidates = clazz.java.enumConstants
            .filter { where(it) }

    when {
        candidates.isEmpty()   -> throw IllegalArgumentException("指定された条件に該当する $clazz の列挙子はみつかりません．")
        candidates.count() > 1 -> throw IllegalArgumentException("指定された条件に該当する $clazz の列挙子が複数みつかりました．")
    }

    return candidates.first()
}
