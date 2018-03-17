package org.yanex.takenoko

interface KoConstructor : KoFunction {
    val isPrimary: Boolean

    val delegateCall: DelegateConstructorCall?
    fun delegateCall(reference: String, vararg args: Any?)

    fun property(
            name: String,
            type: KoType,
            modifiers: KoModifierList,
            initializer: Any? = null,
            block: KoVariable.() -> Unit = {})

    class DelegateConstructorCall(val reference: String, val args: List<Any?>)
}

fun KoConstructor.property(name: String, type: KoType, initializer: Any? = null, block: KoVariable.() -> Unit = {}) {
    property(name, type, KoModifierList.Empty, initializer, block)
}

fun KoConstructor.property(
        name: String,
        type: String,
        defaultValue: Any? = null,
        block: KoVariable.() -> Unit = {}
) {
    property(name, KoType.parseType(type), defaultValue, block)
}

fun KoConstructor.property(
        name: String,
        modifiers: KoModifierList,
        type: String,
        defaultValue: Any? = null,
        block: KoVariable.() -> Unit = {}
) {
    property(name, KoType.parseType(type), modifiers, defaultValue, block)
}
