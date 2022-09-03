# mini-news
Mini project sample for Alterra JVS Bootcamp student

# design database
Silahkan cek di sini: https://dbdiagram.io/d/6306f8a1f1a9b01b0fdbf2f2

# penjelasan skema database
- sebuah post bisa memiliki banyak komentar
- seorang user bisa memberi banyak komentar
- seorang user dapat memiliki banyak role (ROLE_EDITOR, ROLE_READER)
- setiap role bisa dimiliki banyak user

# fitur
- scheduled create posts, dengan pemanggilan API berikut
    ```
    curl https://newsapi.org/v2/top-headlines -G \
            -d country=id \
            -d apiKey=YOUR_API_KEY
    ```
  hasil response akan diantrikan ke rabbitmq untuk kemudian dimasukkan ke database
- dalam proses scheduler, jika ditemukan judul yg sama maka akan tdk akan di-insert
- get posts (paginated)
- get post by id/title
- delete post by ROLE_EDITOR
- create comment by post_id, user_id
- update comment by post_id, user_id
- delete comment by post_id, user_id <user hanya bisa menghapus komentarnya sendiri>
- get comments by post_id
- web socket untuk menampilkan feed 10 berita terbaru
- export statistik artikel (judul, jumlah komentar) dalam excel, di sini akan menggunakan function di Postgres
- dokumentasi API dengan swagger
- implementasi security dg jwt