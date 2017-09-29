// Name:Lucas Rebelo  
// uscid:rebelo@usc.edu
// CSCI 571 HW9
// Spring 2017
//Android Development

//Brief overview of the role of each class/class group:

//Activities:

Main2Activity.java

	Displays the input field, clear and search button, implements the
	navigation drawer menu and asks for location permissions

AboutActivity.java

	Displays USC ID and photo, accessed from navigation drawer menu

ResultActivity.java
	
	Implements the Tab layout, the list adapters, gets user location and
	makes the request to AWS hosted PHP file

FavoriteActivity.java

	Implements the Tab layout, the list adapters, and fetches the data
	stored in SharedPreferences to populate each list

DetailsActivity.java
	
	Fetches the details data, and implements the Tab Layout with the 
	list adapaters for the albums and posts
//Helper classes:

ListModel.java
ListModelPost.java

	The 'Model classes' are used to create the objects that store the 
	essential data from the content (ex: name, image, message,etc...) 
	and are used by the adapters

CustomAdapter.java
CustomAdapterPost.java
ExpandableListAdapter.java

	The ListView/ExpandleListView adapters that link the list views 
	from the layout to the data in the fragments

Response.java
ResponseDetails.java

	Two classes that are used to store the data from the url responses.
	They are used to convert from Json to a java object

TabPagerAdapter.java
TabPagerAdapterDetails.java
TabPagerAdapterFav.java

	The adapters to create the tabs to be displayed and instantiate the 
	fragement classes

AlbumsFragment.java
EventsFragment.java
EventsFragmentFav.java
GroupsFragment.java
GroupsFragmentFav.java
PagesFragment.java
PagesFragmentFav.java
PlacesFragment.java
PlacesFragmentFav.java
PostsFragment.java
UsersFragment.java
UsersFragmentFav.java

	The 'Fragment classes' manage the views from each of the tabs,they 
	use the data from the php request, manipulate it, and attach it to 
	their respective List/ExpandableList views.For each class, they do 
	the necessary UI modifications to display the data

HttpGetRequest.java

	Implementation of AsyncTask