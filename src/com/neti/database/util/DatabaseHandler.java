package com.neti.database.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.neti.question.model.QuestionEtika;
import com.neti.question.model.QuestionGizi;
import com.neti.question.model.QuestionKebersihan;
import com.neti.question.model.QuestionKesehatan;
import com.neti.question.model.QuestionPerkembanganAnak;
import com.neti.question.model.QuestionPerlengkapan;
import com.neti.question.model.QuestionPsikologi;



public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "nannydroid";

    // Login table name
    private static final String TABLE_LOGIN = "login";
    private static final String TABLE_etika = "soal_etika";
	private static final String TABLE_kesehatan = "soal_kesehatan";
	private static final String TABLE_gizi = "soal_gizi"; 
	private static final String TABLE_kebersihan = "soal_kebersihan"; 
	private static final String TABLE_perkembangan_anak = "soal_perkembangan_anak"; 
	private static final String TABLE_perlengkapan = "soal_perlengkapan"; 
	private static final String TABLE_psikologi_anak = "soal_psikologi_anak"; 

    // Login Table Columns names
    private static final String KEY_ID_USER = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_KTP = "ktp";
    private static final String KEY_TTL = "ttl";
    private static final String KEY_EDU = "edu";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "uname";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";
    
    //Question Table Columns names
    private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_A= "a"; //option a
	private static final String KEY_B= "b"; //option b
	private static final String KEY_C= "c"; //option c
	private static final String KEY_D= "d"; //option d
	
	
	// TASK TO CREATE ETIKA TABLE
			private static final String CREATE_TABLE_ETIKA = "CREATE TABLE IF NOT EXISTS " + TABLE_etika + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_A +" TEXT, "
				+KEY_B +" TEXT, "+KEY_C+" TEXT, "+KEY_D+" TEXT)";
		
		// TASK TO CREATE KESEHATAN TABLE
			private static final String CREATE_TABLE_KESEHATAN = "CREATE TABLE IF NOT EXISTS " + TABLE_kesehatan + " ( "
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
					+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_A +" TEXT, "
					+KEY_B +" TEXT, "+KEY_C+" TEXT, "+KEY_D+" TEXT)";
			
		// TASK TO CREATE GIZI TABLE
			private static final String CREATE_TABLE_GIZI = "CREATE TABLE IF NOT EXISTS " + TABLE_gizi + " ( "
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
					+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_A +" TEXT, "
					+KEY_B +" TEXT, "+KEY_C+" TEXT, "+KEY_D+" TEXT)";
			
		// TASK TO CREATE KEBERSIHAN TABLE
			private static final String CREATE_TABLE_KEBERSIHAN = "CREATE TABLE IF NOT EXISTS " + TABLE_kebersihan + " ( "
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
					+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_A +" TEXT, "
					+KEY_B +" TEXT, "+KEY_C+" TEXT, "+KEY_D+" TEXT)";
			
		// TASK TO CREATE PERKEMBANGAN ANAK TABLE
			private static final String CREATE_TABLE_PERKEMBANGAN_ANAK = "CREATE TABLE IF NOT EXISTS " + TABLE_perkembangan_anak + " ( "
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
					+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_A +" TEXT, "
					+KEY_B +" TEXT, "+KEY_C+" TEXT, "+KEY_D+" TEXT)";
			
		// TASK TO CREATE PERLENGKAPAN TABLE
			private static final String CREATE_TABLE_PERLENGKAPAN = "CREATE TABLE IF NOT EXISTS " + TABLE_perlengkapan + " ( "
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
					+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_A +" TEXT, "
					+KEY_B +" TEXT, "+KEY_C+" TEXT, "+KEY_D+" TEXT)";
			
		// TASK TO CREATE PSIKOLOGI ANAK TABLE
			private static final String CREATE_TABLE_PSIKOLOGI = "CREATE TABLE IF NOT EXISTS " + TABLE_psikologi_anak + " ( "
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
					+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_A +" TEXT, "
					+KEY_B +" TEXT, "+KEY_C+" TEXT, "+KEY_D+" TEXT)";
			
			private static final String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + "(" + KEY_ID_USER + " INTEGER PRIMARY KEY,"
	                + KEY_NAME + " TEXT," + KEY_KTP + " TEXT,"  + KEY_TTL + " TEXT,"  + KEY_EDU + " TEXT,"
	                + KEY_EMAIL + " TEXT UNIQUE,"  + KEY_USERNAME + " TEXT,"  + KEY_UID + " TEXT,"
	                + KEY_CREATED_AT + " TEXT" + ")";

			private SQLiteDatabase dbase;
			public DatabaseHandler(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}
			
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	dbase=db;
    	
    	db.execSQL(CREATE_TABLE_ETIKA);	
		db.execSQL(CREATE_TABLE_KESEHATAN);	
		db.execSQL(CREATE_TABLE_GIZI);	
		db.execSQL(CREATE_TABLE_KEBERSIHAN);	
		db.execSQL(CREATE_TABLE_PERKEMBANGAN_ANAK);	
		db.execSQL(CREATE_TABLE_PERLENGKAPAN);	
		db.execSQL(CREATE_TABLE_PSIKOLOGI);	
        db.execSQL(CREATE_TABLE_LOGIN);
		
		addQuestionsEtika();
		addQuestionsKesehatan();
		addQuestionsGizi();
		addQuestionsKebersihan();
		addQuestionsPerkembanganAnak();
		addQuestionsPerlengkapan();
		addQuestionsPsikologi();

    }
    
    private void addQuestionsEtika()
	{
		QuestionEtika q1=new QuestionEtika("1. Seorang babysitter harus memiliki sikap mental " +
				"dan kepribadian yang positip antara lain ?",
				"Bangun pagi sekitar pukul 04.30 WIB",
				"Mulai bekerja sebelum bayi/balita bangun",
				"Menjaga kebersihan badan dan pakaian",
				"Jawaban diatas benar semua",
				"Jawaban diatas benar semua" ); // Correct answer
		this.addQuestionEtika(q1);
		QuestionEtika q2=new QuestionEtika("2. Yang dimaksud etika babysitter adalah ",
				"Babysitter harus mengalah untuk menang",
				"Pandai merawat bayi",
				"Memiliki kepribadian dan tanggung jawab",
				"Dapat menjaga jarak dengan pekerja lain di lingkungan tempatnya bekerja",
				"Memiliki kepribadian dan tanggung jawab"); // Correct answer
		this.addQuestionEtika(q2);
		QuestionEtika q3=new QuestionEtika("3. Alat komunikasi handphone sangat berguna bagi baby sitter" +
				"tetapi yang menyangkut etika ketika akan menggunakannya ,kecuali?",
				"Saat semua tugas sudah selesai dikerjakan baby sitter dapat menggunakan HP nya seperlunya saja",
				"Menggunakan handphone pada saat memberi minum pada bayi bisa mengganggu konsentrasi dan dapat membahayakan bayi",
				"Pada saat anak bermain tidak dapat mengawasinya secara penuh karena harus melayani lawan bicara",
				"Juga peggunaan handpone pada jam kerja,pihak orang tua tidak sangat sakit untuk dapat menghubunginya.",
				"Saat semua tugas sudah selesai dikerjakan baby sitter dapat menggunakan HP nya seperlunya saja"); // Correct answer
		this.addQuestionEtika(q3);
		QuestionEtika q4=new QuestionEtika("4. Setiap ada gejala sakit pada anak yang diasuhnya baby sitter harus melaporkan pada orang tuanya agar" +
				"?","Orang tuanya ikut mengasuh pada saat anaknya sakit",
				"Agar segera diberi tindakan sedini mungkin untuk mencegah hal – hal yang tidak diinginkan",
				"Agar orang tua si anak tidak melarkannya ke yayasan perihal kelakuan baby sitternya",
				"Agar anak yang terkena gejala penyakit sudah bukan wewenang babysitter lagi",
				"Agar segera diberi tindakan sedini mungkin untuk mencegah hal – hal yang tidak diinginkan"); // Correct answer
		this.addQuestionEtika(q4);
		QuestionEtika q5=new QuestionEtika("5. Posisi kerja babysitter yang tidak membahayakan diri dan anak yang diasuhnya adalah ketika h 3,5 menit sebelum disajikan pada bayi adalah " +
				"?","Berjalan membawa bayi atau balita di lantai yang basah dan licin",
				"Ketika babysitter hendak mencuci pakaian bayi atau balita sementara si anak dititipkan pada salah satu anggota keluarga yang lain",
				"Mengajak balita bermain kejar-kejaran melalui tangga",
				"Memberikan minuman obat tanpa mengetahui aturan penggunaanya .",
				"Ketika babysitter hendak mencuci pakaian bayi atau balita sementara si anak dititipkan pada salah satu anggota keluarga yang lain"); // Correct answer
		this.addQuestionEtika(q5);
	}
	
	private void addQuestionsKebersihan()
	{
		QuestionKebersihan q1=new QuestionKebersihan("1. Seorang babysitter harus selalu mencuci tangan menggunakan sabun dengan maksud " +
				"?","Agar kuman – kuman penyebab sakit yang ada ditanganya mati/hilang ",
				"Supaya tangan baby sitter selalu tampak bersih dan lembut.",
				"Supaya terbiasa karena baby sitter hanya melayani keperluan anak",
				"Sudah menjadi aturan yayasan dan pengguna jasa yang harus dipatuhi",
				"Agar kuman – kuman penyebab sakit yang ada ditanganya mati/hilang " ); // Correct answer
		this.addQuestionKebersihan(q1);
		QuestionKebersihan q2=new QuestionKebersihan("2. Cara mensterilkan alat – alat makan dan minum bayi dan balita secara konvensional (direbus pada air dengan suhu 99 derajat celcius,mana yang benar dari pernyataan berikut ini : " +
				"", "Mensterilkan dengan cara direbus pada air mendidih selama 10 menit sampai dengan 15 menit" ,
				"Mensterilkan dengan cara direbus pada air mendidih cukup 1 sampai dengan 5 menit, ", 
				"Mensterilkan cukup dengan dibilas dengan air panas dengan suhu 99 derajat", 
				"Mensterilkan dengan cara direbus pada air hingga saat akan mendidih langsung diangkat.", 
				"Mensterilkan dengan cara direbus pada air hingga saat akan mendidih langsung diangkat."); // Correct answer
		this.addQuestionKebersihan(q2);
		QuestionKebersihan q3=new QuestionKebersihan("3. Manakah pernyataan berikut yang paling benar " +
				" ?","Makanan tersebut sangat dianjurkan untuk membantu pertumbuhan anak karena mengandung vitamin B",
				"Makanan tersebut banyak mengandung protein yang dapat memberikan daya tahan terhadap anak ",
				"Kandungan karbohidrat atau zat gula didalam makanan tersebut dapat mengurangi nafsu makan anak",
				"Makanan tersebut boleh diberikan pada anak karena mengikuti selera anak agar mau makan.",
				"Makanan tersebut sangat dianjurkan untuk membantu pertumbuhan anak karena mengandung vitamin B"); // Correct answer
		this.addQuestionKebersihan(q3);
		QuestionKebersihan q4=new QuestionKebersihan("4. Untuk pembentukan tulang dan gigi pada balita diperlukan unsur kalsium dan faster" +
				" Bahan makanan yang paling banyak mengandung zat tersebut adalah :?",
				"Minyak ikan, kacang hijau, daging sapi",
				"Tahu, tempe, daging ayam, kacang hijau",
				"Ikan kakap, sayur bayam, kangkung",
				"Ikan teri, sayuran hijau, telur, hati ,susu",
				"Minyak ikan , kacang hijau, daging sapi"); // Correct answer
		this.addQuestionKebersihan(q4);
		QuestionKebersihan q5=new QuestionKebersihan("5. Bahan makanan yang mengandung protein adalah  " +
				" ?","Kedelai dan kacang – kacangan ",
				"Jambu dan melon",
				"Wortel dan kentang ",
				"Sirsak dan kol  .",
				"Kedelai dan kacang – kacangan "); // Correct answer
		this.addQuestionKebersihan(q5);
	}
	
	private void addQuestionsGizi()
	{
		QuestionGizi q1=new QuestionGizi("1. Manfaat telur direndam didalam air panas selama kurang lebih 3,5 menit sebelum disajikan pada bayi adalah  " +
				"  ?","Agar vitamin A & D yang ada didalam kuning telur tidak rusak",
				"Agar bakteri yang ada disekitar selaput telur mati dengan adanya pemanasan.",
				"Protein yang terkandung didalam telur juga masih utuh atau tidak rusak karena dipanaskan",
				"Jawaban diatas benar semua",
				"Agar vitamin A & D yang ada didalam kuning telur tidak rusak" ); // Correct answer
		this.addQuestionGizi(q1);
		QuestionGizi q2=new QuestionGizi("2. Berikut ini jenis – jenis buah yang mengandung banyak vitamin C yang bisa diberikan pada bayi dan balita : " +
				"", "Jeruk bali , buah anggur, dan pisang",
				"Tomat , wortel, semangka",
				"Wortel ,jambu, semangka",
				"Melon, nangka , sirsak",
				"Jeruk bali , buah anggur, dan pisang"); // Correct answer
		this.addQuestionGizi(q2);
		QuestionGizi q3=new QuestionGizi("3. Manakah pernyataan berikut yang paling benar " +
				" ?","Makanan tersebut sangat dianjurkan untuk membantu pertumbuhan anak karena mengandung vitamin B", 
				"Makanan tersebut banyak mengandung protein yang dapat memberikan daya tahan terhadap anak ",
				"Kandungan karbohidrat atau zat gula didalam makanan tersebut dapat mengurangi nafsu makan anak",
				"Makanan tersebut boleh diberikan pada anak karena mengikuti selera anak agar mau makan.",
				"Makanan tersebut sangat dianjurkan untuk membantu pertumbuhan anak karena mengandung vitamin B"); // Correct answer
		this.addQuestionGizi(q3);
		QuestionGizi q4=new QuestionGizi("4. Untuk pembentukan tulang dan gigi pada balita diperlukan unsur kalsium dan fosfor " +
				"Bahan makanan yang paling banyak mengandung zat tersebut adalah :?",
				"Minyak ikan , kacang hijau, daging sapi",
				"Tahu ,tempe, daging ayam, kacang hijau",
				"Ikan kakap, sayur bayam, kangkung",
				"Ikan teri, sayuran hijau, telur, hati ,susu",
				"Minyak ikan , kacang hijau, daging sapi"); // Correct answer
		this.addQuestionGizi(q4);
		QuestionGizi q5=new QuestionGizi("5. Bahan makanan yang mengandung protein adalah  " +
				" ?","Kedelai dan kacang – kacangan ",
				"Jambu dan melon",
				"Wortel dan kentang ",
				"Sirsak dan kol  .",
				"Kedelai dan kacang – kacangan "); // Correct answer
		this.addQuestionGizi(q5);
	}
	
	private void addQuestionsKesehatan()
	{
		QuestionKesehatan q1=new QuestionKesehatan("1. Obat penurun panas dapat dihentikan pada anak setelah  " +
				"  ?","Ada keterangan dari dokter", 
				"Suhu tubuhnya telah mencapai 38,5 derajat celcius",
				"Obatnya sebelum kadaluarsa",
				"Jawaban diatas benar semua",
				"Suhu tubuhnya telah mencapai 38,5 derajat celcius" ); // Correct answer
		this.addQuestionKesehatan(q1);
		QuestionKesehatan q2=new QuestionKesehatan("2. Jika pada etiket obat tertulis 3 x 1 sendok teh berarti : " +
				"", "Setiap 3 hari diberi obat 1 sendok teh",
				"Setiap 3 sendok teh sekali minum",
				"Setiap hari 3 kali masing – masing 1 sendok teh",
				"Setiap hari 3 kali masing – masing 3 sendok teh",
				"Setiap hari 3 kali masing – masing 1 sendok teh"); // Correct answer
		this.addQuestionKesehatan(q2);
		QuestionKesehatan q3=new QuestionKesehatan("3. Salah satu gejala penyakit yang cukup berbahaya dan mudah menular  " +
				"adalah muntaber dan cholera. Ciri – cirinya adalah ?",
				"Kekejangan pada dagu dan punggung si penderita", 
				"Suhu tubuh tinggi dan buang – buang air besar",
				"Batuk – batuk",
				"Gatal – gatal pada sejumlah anggota tubuh",
				"Suhu tubuh tinggi dan buang – buang air besar"); // Correct answer
		this.addQuestionKesehatan(q3);
		QuestionKesehatan q4=new QuestionKesehatan("4. Penyakit langganan pada anak diantaranya penyait flu, pilek,batuk dan demam. Jika anak mengalami gejala penyakit tersebut maka tindakan yang bisa dilakukan adalah " +
				"  :?",	"Jika ada gejala demam panas maka harus dilaporkan pada kedua orang tuanya",
				"Jika ada gejala suhu tubuhnya sudah diatas 37 derajat maka harus segera dikompres dengan air hangat",
				"Berikan obat – obatan yang sudah di rekomendasikan oleh dokter",
				"Jawaban diatas benar semua ",
				"Jika ada gejala suhu tubuhnya sudah diatas 37 derajat maka harus segera dikompres dengan air hangat"); // Correct answer
		this.addQuestionKesehatan(q4);
		QuestionKesehatan q5=new QuestionKesehatan("5, Cara mensterilkan alat – alat makan dan minum bayi dan balita secara konvensional (direbus pada air dengan suhu 99 derajat celcius,mana yang benar dari pernyataan berikut ini  " +
				" ?","Mensterilkan dengan cara direbus pada air mendidih selama 10 menit sampai dengan 15 menit ",
				"Mensterilkan dengan cara direbus pada air mendidih cukup 1 sampai dengan 5 menit",
				"Mensterilkan cukup dengan dibilas dengan air panas dengan suhu 99 derajat ",
				"Mensterilkan dengan cara direbus pada air hingga saat akan mendidih langsung diangkat.",
				"Mensterilkan dengan cara direbus pada air mendidih cukup 1 sampai dengan 5 menit "); // Correct answer
		this.addQuestionKesehatan(q5);
	}
	
	private void addQuestionsPerkembanganAnak()
	{
		QuestionPerkembanganAnak q1=new QuestionPerkembanganAnak("1. Masih dalam lingkup bermain untuk anak baby sitter juga harus dapat memberikan kegiatan pada anak yang diasuhnya antara lain kecuali  " +
				"  ?","Berlatih bernyayi, bercerita, berhitung dll", 
				"Keterapilan melipat kertas (kapal, pesawat dll)",
				"Mewarnai menggambar dan menulis",
				"Menonton tv (sinetron)",
				"Berlatih bernyayi, bercerita, berhitung dll" ); // Correct answer
		this.addQuestionPerkembanganAnak(q1);
		QuestionPerkembanganAnak q2=new QuestionPerkembanganAnak("2. Jika berat bayi usia 3 bulan adalah antara 4 sampai dengan 5 kg maka rata – rata beratnya adalah 4,5 kg. Maka jika ada bayi berumur 3 bulan  " +
				"", "Beratnya diatas normal",
				"Beratnya normal",
				"Beratnya dibawah normal", 
				"Benar semua",
				"Beratnya dibawah normal"); // Correct answer
		this.addQuestionPerkembanganAnak(q2);
		QuestionPerkembanganAnak q3=new QuestionPerkembanganAnak("3. Menggambar bagi anak – anak adalah sebagai tahapan awal untuk mengenal alat-alat tulis seperti buku dan pensil atau pulpen, tetapi ada hal yang lebih penting lagi yaitu kecuali " +
				" ?","Supaya kelak bisa menjadi pelukis terkenal",
				"Terlatih ketelitian anak dalam mengamati sesuatu dilingkungan yang dinyatakan dalam gambar",
				"Anak mendapat kesempatan untuk dapat mengekspresikan dalam bentuk coret – coretan",
				"Selain anak mendapat keterampilan anak akan dapat menambah  pengertian dan pengetahuan.",
				"Supaya kelak bisa menjadi pelukis terkenal"); // Correct answer
		this.addQuestionPerkembanganAnak(q3);
		QuestionPerkembanganAnak q4=new QuestionPerkembanganAnak("4. Mainan untuk bayi umur 13 sampai dengan 15 bulan sepeti mainan mobil –mobilan ,mainan yang didorong atau kereta –keretaan manfaatnya adalah r" +
				" ?","Membantu bayi mengenal dirinya ",
				"Membantu untuk pertumbuhan giginya",
				"Mengajarkan keseimbangan untuk berjalan",
				"Menciptakan suasana bermain yang berkesan",
				"Membantu bayi mengenal dirinya "); // Correct answer
		this.addQuestionPerkembanganAnak(q4);
		QuestionPerkembanganAnak q5=new QuestionPerkembanganAnak("5. perlengkapan yang perlu dibawa pada saat balita akan diajak bepergian untuk sekitar 2 hari adalah kecuali " +
				" ?","Buku , alat tulis , tas, sabun cuci",
				"Makanan balita, mainan",
				"Termos , dan pakaianya",
				"Obat – obatan dan kosmetiknya .",
				"Termos, dan pakaianya"); // Correct answer
		this.addQuestionPerkembanganAnak(q5);
	}
	
	private void addQuestionsPerlengkapan()
	{
		QuestionPerlengkapan q1=new QuestionPerlengkapan("1. Beberapa perlengkapan bayi dan balita adalah kecuali" +
				"  ?","Stroller",
				"Baby box",
				"Uriner",
				"Blender ",
				"Blender"); // Correct answer
		this.addQuestionPerlengkapan(q1);
		QuestionPerlengkapan q2=new QuestionPerlengkapan("2. Perlengkapan bayi dan balita berikut ini tergolong jenis obat ,kecuali   " +
				"", "Kolonyet",
				"Boor water ",
				"Betadine ",
				"Alkohol ",
				"Alkohol "); // Correct answer
		this.addQuestionPerlengkapan(q2);
		QuestionPerlengkapan q3=new QuestionPerlengkapan("3. Mana yang merupakan perlengkapan tidur  bayi  " +
				" ?","Handuk, kain gendong, selimut ",
				"Selimut, bantal, guling ",
				"Baju tidur, maman, kain gendong",
				"Buku cerita , bantal mainan .",
				"Baju tidur, maman, kain gendong"); // Correct answer
		this.addQuestionPerlengkapan(q3);
		QuestionPerlengkapan q4=new QuestionPerlengkapan("4. Syarat alat mainan anak – anak adalah , kecuali " +
				" ?","Tidak tajam , dan tidak tuncing ",
				"Mainan yang cocok untuk anak – anak adalah kelereng",
				"Beraneka ragam warna dan jenisnya",
				"Cocok dengan usia serta jenisnya",
				"Mainan yang cocok untuk anak – anak adalah kelereng "); // Correct answer
		this.addQuestionPerlengkapan(q4);
		QuestionPerlengkapan q5=new QuestionPerlengkapan("5. Benda – benda berikut tidak membahayakan bagi bayi dan balita apabila digunakan olehnya " +
				" ?","Benda tajam ",
				"Benda runcing ",
				"Air panas , caran obat atau kimia",
				"Gas oksigen yang ada diudara.",
				"Gas oksigen yang ada diudara"); // Correct answer
		this.addQuestionPerlengkapan(q5);
	}
	
	private void addQuestionsPsikologi()
	{
		QuestionPsikologi q1=new QuestionPsikologi("1. Seorang anak yang belum bisa berbicara dia akan menyampaikan keinginanya melalui proses prabicara. " +
				"Bentuk – bentuk prabicara ini umum dinyatakan oleh seorang anak balita yang ingin menyampaikan keinginanya kecuali : ?",
				"Menangis", 
				"Bahasa isyarat",
				"Merangkak ",
				"Berceloteh",
				"Berceloteh"); // Correct answer
		this.addQuestionPsikologi(q1);
		QuestionPsikologi q2=new QuestionPsikologi("2. Babysitter harus mengerti kemauan seorang anak yang diasuhnya. Biasanya setiap balita akan menunjukan emosi marahnya. Jika keinginanya tidak terpenuhi manakah yang bukan termasih emosi marahnya Jika keinginanya tidak terpenuhi manakah yang bukan termasuk emosi marah? " +
				"", "Merengek",
				"Menjerit",
				"Memukul", 
				"Berguling –guling",
				"Memukul"); // Correct answer
		this.addQuestionPsikologi(q2);
		QuestionPsikologi q3=new QuestionPsikologi("3. Beberapa hal yang berkaitan dengan perkembangan emosi anak disebut afeksi. Artinya adalah : " +
				" ?","Cemburu",
				"Kasih sayang ",
				"Cerdas ",
				"Depresi ",
				"Kasih sayang"); // Correct answer
		this.addQuestionPsikologi(q3);
		QuestionPsikologi q4=new QuestionPsikologi("4. Mainan untuk bayi umur 13 sampai dengan 15 bulan sepeti mainan mobil –mobilan ,mainan yang didorong atau kereta –keretaan manfaatnya adalah r" +
				" ?",	"Membantu bayi mengenal dirinya",
				"Membantu untuk pertumbuhan giginya",
				"Mengajarkan keseimbangan untuk berjalan",
				"Menciptakan suasana bermain yang berkesan",
				"Membantu bayi mengenal dirinya"); // Correct answer
		this.addQuestionPsikologi(q4);
		QuestionPsikologi q5=new QuestionPsikologi("5. Perlengkapan yang perlu dibawa pada saat balita akan diajak bepergian untuk sekitar 2 hari adalah kecuali " +
				" ?","Buku , alat tulis , tas, sabun cuci",
				"Makanan balita, mainan",
				"Termos , dan pakaianya",
				"Obat – obatan dan ksmetiknya",
				"Termos , dan pakaianya"); // Correct answer
		this.addQuestionPsikologi(q5);
	}
	

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_etika);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_gizi);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_kebersihan);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_perkembangan_anak);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_psikologi_anak);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_perlengkapan);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_kesehatan);
        // Create tables again
        onCreate(db);
    }
    
 // Adding new question
 	public void addQuestionEtika(QuestionEtika quest) {
 		//SQLiteDatabase db = this.getWritableDatabase();
 		ContentValues values = new ContentValues();
 		values.put(KEY_QUES, quest.getQUESTION()); 
 		values.put(KEY_ANSWER, quest.getANSWER());
 		values.put(KEY_A, quest.getA());
 		values.put(KEY_B, quest.getB());
 		values.put(KEY_C, quest.getC());
 		values.put(KEY_D, quest.getD());
 		// Inserting Row
 		dbase.insert(TABLE_etika, null, values);		
 	}
 	// Adding new question
 	public void addQuestionKebersihan(QuestionKebersihan quest) {
 			//SQLiteDatabase db = this.getWritableDatabase();
 			ContentValues values = new ContentValues();
 			values.put(KEY_QUES, quest.getQUESTION()); 
 			values.put(KEY_ANSWER, quest.getANSWER());
 			values.put(KEY_A, quest.getA());
 			values.put(KEY_B, quest.getB());
 			values.put(KEY_C, quest.getC());
 			values.put(KEY_D, quest.getD());
 			// Inserting Row
 			dbase.insert(TABLE_kebersihan, null, values);		
 		}
 	// Adding new question
 	public void addQuestionGizi(QuestionGizi quest) {
 			//SQLiteDatabase db = this.getWritableDatabase();
 			ContentValues values = new ContentValues();
 			values.put(KEY_QUES, quest.getQUESTION()); 
 			values.put(KEY_ANSWER, quest.getANSWER());
 			values.put(KEY_A, quest.getA());
 			values.put(KEY_B, quest.getB());
 			values.put(KEY_C, quest.getC());
 			values.put(KEY_D, quest.getD());
 			// Inserting Row
 			dbase.insert(TABLE_gizi, null, values);		
 		}
 	// Adding new question
 	public void addQuestionKesehatan(QuestionKesehatan quest) {
 			//SQLiteDatabase db = this.getWritableDatabase();
 			ContentValues values = new ContentValues();
 			values.put(KEY_QUES, quest.getQUESTION()); 
 			values.put(KEY_ANSWER, quest.getANSWER());
 			values.put(KEY_A, quest.getA());
 			values.put(KEY_B, quest.getB());
 			values.put(KEY_C, quest.getC());
 			values.put(KEY_D, quest.getD());
 			// Inserting Row
 			dbase.insert(TABLE_kesehatan, null, values);		
 		}
 	// Adding new question
 	public void addQuestionPerkembanganAnak(QuestionPerkembanganAnak quest) {
 			//SQLiteDatabase db = this.getWritableDatabase();
 			ContentValues values = new ContentValues();
 			values.put(KEY_QUES, quest.getQUESTION()); 
 			values.put(KEY_ANSWER, quest.getANSWER());
 			values.put(KEY_A, quest.getA());
 			values.put(KEY_B, quest.getB());
 			values.put(KEY_C, quest.getC());
 			values.put(KEY_D, quest.getD());
 			// Inserting Row
 			dbase.insert(TABLE_perkembangan_anak, null, values);		
 		}
 	// Adding new question
 	public void addQuestionPerlengkapan(QuestionPerlengkapan quest) {
 			//SQLiteDatabase db = this.getWritableDatabase();
 			ContentValues values = new ContentValues();
 			values.put(KEY_QUES, quest.getQUESTION()); 
 			values.put(KEY_ANSWER, quest.getANSWER());
 			values.put(KEY_A, quest.getA());
 			values.put(KEY_B, quest.getB());
 			values.put(KEY_C, quest.getC());
 			values.put(KEY_D, quest.getD());
 			// Inserting Row
 			dbase.insert(TABLE_perlengkapan, null, values);		
 		}
 	// Adding new question
 	public void addQuestionPsikologi(QuestionPsikologi quest) {
 			//SQLiteDatabase db = this.getWritableDatabase();
 			ContentValues values = new ContentValues();
 			values.put(KEY_QUES, quest.getQUESTION()); 
 			values.put(KEY_ANSWER, quest.getANSWER());
 			values.put(KEY_A, quest.getA());
 			values.put(KEY_B, quest.getB());
 			values.put(KEY_C, quest.getC());
 			values.put(KEY_D, quest.getD());
 			// Inserting Row
 			dbase.insert(TABLE_psikologi_anak, null, values);		
 		}
 	
 	public List<QuestionEtika> getAllQuestionEtika() {
		List<QuestionEtika> quesList = new ArrayList<QuestionEtika>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_etika;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionEtika quest = new QuestionEtika();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setA(cursor.getString(3));
				quest.setB(cursor.getString(4));
				quest.setC(cursor.getString(5));
				quest.setD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public List<QuestionGizi> getAllQuestionGizi() {
		List<QuestionGizi> quesList = new ArrayList<QuestionGizi>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_gizi;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionGizi quest = new QuestionGizi();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setA(cursor.getString(3));
				quest.setB(cursor.getString(4));
				quest.setC(cursor.getString(5));
				quest.setD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public List<QuestionKebersihan> getAllQuestionKebersihan() {
		List<QuestionKebersihan> quesList = new ArrayList<QuestionKebersihan>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_kebersihan;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionKebersihan quest = new QuestionKebersihan();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setA(cursor.getString(3));
				quest.setB(cursor.getString(4));
				quest.setC(cursor.getString(5));
				quest.setD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public List<QuestionKesehatan> getAllQuestionKesehatan() {
		List<QuestionKesehatan> quesList = new ArrayList<QuestionKesehatan>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_kesehatan;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionKesehatan quest = new QuestionKesehatan();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setA(cursor.getString(3));
				quest.setB(cursor.getString(4));
				quest.setC(cursor.getString(5));
				quest.setD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public List<QuestionPerkembanganAnak> getAllQuestionPerkembanganAnak() {
		List<QuestionPerkembanganAnak> quesList = new ArrayList<QuestionPerkembanganAnak>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_perkembangan_anak;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionPerkembanganAnak quest = new QuestionPerkembanganAnak();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setA(cursor.getString(3));
				quest.setB(cursor.getString(4));
				quest.setC(cursor.getString(5));
				quest.setD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public List<QuestionPerlengkapan> getAllQuestionPerlengkapan() {
		List<QuestionPerlengkapan> quesList = new ArrayList<QuestionPerlengkapan>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_perlengkapan;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionPerlengkapan quest = new QuestionPerlengkapan();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setA(cursor.getString(3));
				quest.setB(cursor.getString(4));
				quest.setC(cursor.getString(5));
				quest.setD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public List<QuestionPsikologi> getAllQuestionPsikologi() {
		List<QuestionPsikologi> quesList = new ArrayList<QuestionPsikologi>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_psikologi_anak;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionPsikologi quest = new QuestionPsikologi();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setA(cursor.getString(3));
				quest.setB(cursor.getString(4));
				quest.setC(cursor.getString(5));
				quest.setD(cursor.getString(6));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_etika;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}

    /**
     * Storing user details in database
     * */
    public void addUser(String name, String ktp, String ttl, String edu, String email, String uname, String uid, String created_at) {
        dbase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // FirstName
        values.put(KEY_KTP, ktp); // LastName
        values.put(KEY_TTL, ttl);
        values.put(KEY_EDU, edu);
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_USERNAME, uname); // UserName
        values.put(KEY_UID, uid); // Email
        values.put(KEY_CREATED_AT, created_at); // Created At

        // Inserting Row
        dbase.insert(TABLE_LOGIN, null, values);
        dbase.close(); // Closing database connection
    }


    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("name", cursor.getString(1));
            user.put("ktp", cursor.getString(2));
            user.put("ttl", cursor.getString(3));
            user.put("edu", cursor.getString(4));
            user.put("email", cursor.getString(5));
            user.put("uname", cursor.getString(6));
            user.put("uid", cursor.getString(7));
            user.put("created_at", cursor.getString(8));
        }
        cursor.close();
        dbase.close();
        // return user
        return user;
    }



    /**
     * Getting user login status
     * return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        dbase.close();
        cursor.close();

        // return row count
        return rowCount;
    }


    /**
     * Re crate database
     * Delete all tables and create them again
     * */
    public void resetTables(){
    	dbase = this.getWritableDatabase();
        // Delete All Rows
        dbase.delete(TABLE_LOGIN, null, null);
        dbase.close();
    }

}
