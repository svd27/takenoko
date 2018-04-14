package org.yanex.takenoko

interface KoImportList {
    val imports: Map<String, String>
    var extra : Set<String>
    operator fun get(type: KoType): KoType
    fun extra(i:String) { extra += i }
}

operator fun KoImportList.get(type: String): KoType {
    return get(KoType.parseType(type))
}