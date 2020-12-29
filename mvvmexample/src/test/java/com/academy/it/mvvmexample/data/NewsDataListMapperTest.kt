package com.academy.it.mvvmexample.data

import org.junit.Assert.*
import org.junit.Test

class NewsDataListMapperTest{

    private val mapper = NewsDataListMapper()

    private val json = """
        {
	        "status": "ok",
	        "totalResults": 70,
	        "articles": [
                {
                    "source ": {
			            "id": null,
			            "name": "CNBC"
		            },
		            "author": "Sam Shead",
		            "title": "TITLE",
		            "description": "description",
		            "url": "url",
		            "urlToImage": "https://image.cnbcfm.com/api/v1/image/104712662-graphcore_founders.jpg?v=1505490455",
		            "publishedAt": "2020-12-29T11:25:00Z",
		            "content": "Graphcore founders Simon Knowles and Nigel Toon\r\nLONDON U.K.-based chipmaker Graphcore announced Tuesday that it had raised ${'$'}222 million of investment as it looks to take on U.S rivals Nvidia and Int… [+4842 chars]"
	            }
            ]
        }
    """.trimIndent()

    @Test
    fun validateMApperWithJson(){
        val result = mapper.invoke(json)
        assertEquals(1, result.size)
    }

    @Test
    fun `validate mapped object`() {
        val result = mapper.invoke(json)
        val expectedObject = result[0]

        assertEquals("TITLE", expectedObject.title)
        assertEquals("description", expectedObject.description)
        assertEquals("url", expectedObject.url)
    }
}