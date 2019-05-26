package com.rolly


import org.junit.Test

class CommentModelTests {
    @Test
    fun deleteCommentTest(){
        val pool = ConnectionPool("jdbc:h2:mem:testd;MODE=MYSQL", null, null)
        val model = CommentMysqlModel(pool)

        pool.useConnection { connection ->

            connection.prepareStatement("CREATE TABLE article (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  text TEXT NOT NULL,\n" +
                    "PRIMARY KEY (`id`));"
            ).use { it.execute() }



            connection.prepareStatement("CREATE TABLE comment (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  text TEXT NOT NULL,\n" +
                    "  id_article INT NOT NULL,\n"+
                    "PRIMARY KEY (`id`),\n" +
                    "FOREIGN KEY (id_article)\n" +
                    "        REFERENCES article(id)\n" +
                    ")ENGINE=INNODB;"
            ).use { it.execute() }



            connection.prepareStatement("INSERT INTO article (id, text) VALUES (1, 'Article');").use { it.execute() }
            connection.prepareStatement("INSERT INTO comment (id, text, id_article) VALUES (1, 'Article', 1);").use { it.execute() }


            model.deleteComment(1)

        }
    }

}