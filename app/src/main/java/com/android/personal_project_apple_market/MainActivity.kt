package com.android.personal_project_apple_market

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.personal_project_apple_market.adapter.RecyclerViewAdapter
import com.android.personal_project_apple_market.databinding.ActivityMainBinding
import com.android.personal_project_apple_market.databinding.RecyclerItemBinding
import com.android.personal_project_apple_market.model.Apple
import java.text.DecimalFormat

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // data
        val list = mutableListOf<Apple>()
        list.add(Apple(R.drawable.sample1,"대현동", "산지 한달된 선풍기 팝니다","이사가서 필요가 없어졌어요 급하게 내놓습니다",1000,"서울 서대문구 창천동",25,13,false))
        list.add(Apple(R.drawable.sample2, "안마담","김치냉장고","이사로인해 내놔요",20000,"인천 계양구 귤현동",28,8,false))
        list.add(Apple(R.drawable.sample3, "코코유","샤넬 카드지갑","고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",10000,"수성구 범어동",5,23,false))
        list.add(Apple(R.drawable.sample4,"Nicole","금고","금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다",10000,"해운대구 우제2동",17,14,false))
        list.add(Apple(R.drawable.sample5, "절명","갤럭시Z플립3 팝니다","갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",150000,"연제구 연산제8동",9,22,false))
        list.add(Apple(R.drawable.sample6, "미니멀하게","프라다 복조리백","까임 오염없고 상태 깨끗합니다\n정품여부모름",50000,"수원시 영통구 원천동",16,25,false))
        list.add(Apple(R.drawable.sample7, "굿리치","울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장","울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",150000,"남구 옥동",54,142,false))
        list.add(Apple(R.drawable.sample8, "난쉽","샤넬 탑핸들 가방","샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n\n색상 : 블랙\n사이즈 : 25.5cm * 17.5cm * 8cm\n구성 : 본품, 더스트\n\n급하게 돈이 필요해서 팝니다 ㅠ ㅠ",180000,"동래구 온천제2동",7,31,false))
        list.add(Apple(R.drawable.sample9, "알뜰한","4행정 엔진분무기 판매합니다.","3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n",30000,"원주시 명륜2동",28,7,false))
        list.add(Apple(R.drawable.sample10,"똑태현","셀린느 버킷 가방","22년 신세계 대전 구매입니당\n" + "셀린느 버킷백\n" + "구매해서 몇번사용했어요\n" + "까짐 스크래치 없습니다.\n" + "타지역에서 보내는거라 택배로 진행합니당!\n",190000,"중구 동화동",6,40,false))

        // recyclerview
        val adapter = RecyclerViewAdapter(list)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // notification
        val bell = findViewById<ImageView>(R.id.main_bell_iv)
        bell.setOnClickListener {
            notification()
        }

        // onClick
        val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val position = it.data?.getIntExtra("position", 0) as Int
                val isLike = it.data?.getBooleanExtra("isLike", false) as Boolean

                if (isLike) {
                    list[position].isLike = true
                    list[position].like += 1
                } else {
                    if (list[position].isLike) {
                        list[position].isLike = false
                        list[position].like -= 1
                    }

                }
                adapter.notifyItemChanged(position)
            }
        }

        adapter.itemClick = object : RecyclerViewAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("position", position)
                intent.putExtra("item", list[position])
                activityResultLauncher.launch(intent)
            }
        }



        // fab
        val onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    binding.btnFab.show()
                    binding.btnFab.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN))

                    binding.btnFab.setOnClickListener {
                        binding.recyclerView.smoothScrollToPosition(0)
                        binding.btnFab.setBackgroundTintList(ColorStateList.valueOf(Color.MAGENTA))
                    }
                }
                else {
                    binding.btnFab.hide()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        }
        binding.recyclerView.addOnScrollListener(onScrollListener)
    }

    // 뒤로가기 x dialog
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("종료")
            builder.setMessage("정말 종료하시겠습니까?")
            builder.setIcon(R.drawable.ic_bubble_chat)

            builder.setPositiveButton("확인", null)
            builder.setNegativeButton("취소", null)
            builder.show()

            return true
        }
        return false
    }

    // converter 1,000 comma
    fun converter(num: Int) : String {
        val df = DecimalFormat("#,###")
        return df.format(num)
    }

    // notification()
    fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)

        } else {
            builder = NotificationCompat.Builder(this)
        }

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ipad)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("아이패드 파격세일")
            setContentText("오늘만 이 가격에 팔아요!")
            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        "오늘만 이 가격에 팔아요! 더 이상의 네고는 힘듭니다. 구성품은 본체, 충전기, 박스 포함해서 풀박입니다. " +
                                "거래는 서구청 앞 벤치에서 했으면 해요!"
                    )
            )
            setLargeIcon(bitmap)
            addAction(R.mipmap.ic_launcher, "Action", pendingIntent)
        }
        manager.notify(11, builder.build())
    }
}