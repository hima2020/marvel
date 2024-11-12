package org.rawafedtech.marvelapp.utils


interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}


class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (Throwable?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {
    private var currentKey = initialKey
    private var isMakingRequest = false
    override suspend fun loadNextItems() {


        onLoadUpdated(true)
        val result = try {
            onRequest(currentKey)
        } catch (e: Throwable) {
            onError(e)
            onLoadUpdated(false)
            isMakingRequest = false
            return
        }
        if (result.isSuccess){
            isMakingRequest = false
            val items = result.getOrElse {
                onError(it)
                onLoadUpdated(false)
                return
            }
            currentKey = getNextKey(items)
            onSuccess(items, currentKey)
            onLoadUpdated(false)
        }
        else {
            isMakingRequest = false
            onError(Throwable(""))

        }




    }

    override fun reset() {
        currentKey = initialKey
    }
}