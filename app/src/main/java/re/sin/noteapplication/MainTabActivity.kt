package re.sin.noteapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import re.sin.noteapplication.view.MainSectionsPagerAdapter
import re.sin.noteapplication.databinding.ActivityMainTabBinding

class MainTabActivity : AppCompatActivity() {
    private var _binding: ActivityMainTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.viewPager.apply {
            adapter = MainSectionsPagerAdapter(this@MainTabActivity)
        }

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = when(position){
                1 -> "Folder"
                else -> "All"
            }
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}