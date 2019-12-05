# TargetApp
MVVM-RxJava-LiveData-Room-Navigation Component

# OverView
![giphy](https://user-images.githubusercontent.com/6108274/70237754-eb55bb00-1778-11ea-8dcf-855d20dd34db.gif)

 # Uses the following technologies
 
  -ViewModel
  -LiveData
  -Room
  -Navigation Component
  -RxJava 2
  
  
  # How did I use navigation component?
  
  
  NavigationUI class  includes methods for  managing  the navigation drawer, and bottom navigation.
  I used navigation drawer and bottom navigation.
  I created  two navigation graph on this project.
  
  <img width="464" alt="Screen Shot 2019-12-05 at 16 23 59" src="https://user-images.githubusercontent.com/6108274/70244178-f57db680-1784-11ea-983d-35d75bb29ef5.png">
  
  Navigation also allow for passing data between destinations.
  
 
<img width="619" alt="Screen Shot 2019-12-05 at 16 28 30" src="https://user-images.githubusercontent.com/6108274/70244233-0dedd100-1785-11ea-96a0-2da5158cb735.png">
 

  I reached to navigation graph inside fragment for passing data

 var bundle = bundleOf("targetArgs" to targetText,"targetArgsDate" to targetDate)
                             
 view?.findNavController()?.navigate(com.example.targetapp.R.id.action_addNewTaskFragment_to_bottomNavFragmentAdd, bundle)
                
  
  
   # How did I use Room ?
   
   I used room 2 with RxJava.
  
  
  
  
  
  # Usage of  night mode 
  
  https://developer.android.com/guide/topics/ui/look-and-feel/darktheme
  
  -values
 <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
 
 
 -values-night
 <style name="AppTheme" parent="Theme.AppCompat.DayNight.DarkActionBar">
  

<img width="695" alt="Screen Shot 2019-12-05 at 17 26 05" src="https://user-images.githubusercontent.com/6108274/70243846-72f4f700-1784-11ea-93b0-06a6c0c84abe.png">

  I created two values folder for this project.Each folder inside color,themes dimens and style xml file.
   Android 10 settings selected dark theme automatic night mode supported.
  Besides, it can changes inside application settings dark theme.
 

  
  
  
  
  
  
  
  
  
 

