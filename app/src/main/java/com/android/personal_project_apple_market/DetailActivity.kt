package com.android.personal_project_apple_market

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.personal_project_apple_market.databinding.ActivityDetailBinding
import com.android.personal_project_apple_market.model.Apple

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // "매너온도" TextView에 밑줄 쳐주기
        binding.tvManner.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        // 인텐트에서 값 받아오기
        val item = intent.getParcelableExtra<Apple>("item") as Apple

        // 받아온 값으로 뷰 매핑
        binding.ivPicture.setImageResource(item.picture)
        binding.tvWriter.text = "${item.writer}"
        binding.address.text = "${item.address}"
        binding.detailTvTitle.text = "${item.title}"
        binding.detailTvContent.text = "${item.content}"
        binding.detailTvPrice.text = "${MainActivity().converter(item.price)}" + "원"

        // 뒤로가기 기능 구현
        binding.ivBack.setOnClickListener {
            finish()
        }

    }
}