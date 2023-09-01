package com.android.personal_project_apple_market

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.personal_project_apple_market.databinding.ActivityDetailBinding
import com.android.personal_project_apple_market.model.Apple
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var isLike = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // "매너온도" TextView에 밑줄 쳐주기
        binding.tvManner.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        // 인텐트에서 값 받아오기
        val item = intent.getParcelableExtra<Apple>("item") as Apple
        val position = intent.getIntExtra("position", 0)

        // 받아온 값으로 뷰 매핑
        binding.ivPicture.setImageResource(item.picture)
        binding.tvWriter.text = "${item.writer}"
        binding.address.text = "${item.address}"
        binding.detailTvTitle.text = "${item.title}"
        binding.detailTvContent.text = "${item.content}"
        binding.detailTvPrice.text = "${MainActivity().converter(item.price)}" + "원"


        // 뒤로가기 기능 구현 (이미지뷰)
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("isLike", isLike)

            setResult(RESULT_OK, intent)
            finish()
        }

        // 좋아요
        isLike = item.isLike

        binding.detailLikeImageview.setImageResource(
            if (item.isLike) {
                R.drawable.ic_heart2
            } else {
                R.drawable.ic_heart
            }
        )

        binding.detailLikeImageview.setOnClickListener {
            if (!isLike) {
                binding.detailLikeImageview.setImageResource(R.drawable.ic_heart2)
                Snackbar.make(binding.detailPage, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                isLike = true
            } else {
                binding.detailLikeImageview.setImageResource(R.drawable.ic_heart)
                isLike = false
            }
        }
    }

    // 뒤로가기 기능 구현 (물리 버튼)
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        val position = intent.getIntExtra("position", 0)
        intent.putExtra("position", position)
        intent.putExtra("isLike", isLike)

        setResult(RESULT_OK, intent)
        finish()
    }
}

