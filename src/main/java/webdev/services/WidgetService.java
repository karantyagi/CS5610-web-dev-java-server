package webdev.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Lesson;
import webdev.models.Topic;
import webdev.models.User;
import webdev.models.Widget;

import webdev.repositories.TopicRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	TopicRepository topicRepository;

	@Autowired
	WidgetRepository widgetRepository;
	
@GetMapping("/api/widget")
	public List<Widget> findAllWidgets()
	{
		List<Widget> all = new ArrayList<>();
		all = (List<Widget>) widgetRepository.findAll();
		all.sort(Comparator.comparing(Widget::getWidgetOrder));
//		all.sort(Comparator.comparing(Widget::getWidgetOrder).reversed());
		return all;
	}


@GetMapping("/api/topic/{topicId}/widget")
public List<Widget> findAllWidgetsForTopic(
		@PathVariable("topicId") int topicId) {
	List<Widget> empty = new ArrayList<>();
	Optional<Topic> data = topicRepository.findById(topicId);
	if(data.isPresent()) {
		Topic topic = data.get();
		List<Widget> all = topic.getWidgets();
		all.sort(Comparator.comparing(Widget::getWidgetOrder));
		return all;
	}
	
	return empty;		
}


@PutMapping("/api/widget/{widgetId}/save")
public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {
	
	System.out.println("\n\n\n WIDGET ID: "+widgetId+"\n");
	Optional<Widget> data = widgetRepository.findById(widgetId);
	if(data.isPresent()) {
		Widget widget = data.get();
//		widget.setId(newWidget.getId());
		widget.setTopic(newWidget.getTopic());
		widget.setClassName(newWidget.getClassName());
		widget.setHeight(newWidget.getHeight());
		widget.setHref(newWidget.getHref());
		widget.setListItems(newWidget.getListItems());
		widget.setListType(newWidget.getListType());
		widget.setName(newWidget.getName());
		widget.setSize(newWidget.getSize());
		widget.setSrc(newWidget.getSrc());
		widget.setStyle(newWidget.getStyle());
		widget.setText(newWidget.getText());
		widget.setWidth(newWidget.getWidth());
		widget.setWidgetType(newWidget.getWidgetType());
		widget.setWidgetOrder(newWidget.getWidgetOrder());
		widgetRepository.save(widget);
		
		System.out.println("\n\n"+widget);
		return widget;
	}
	return null;
}


@DeleteMapping("/api/widget/{widgetId}")
public void deleteWidgetById(@PathVariable("widgetId") int widgetId)
{
	widgetRepository.deleteById(widgetId);
}

@DeleteMapping("/api/widget/delete")
public void deleteWidget(@RequestBody Widget widget)
{
	System.out.println("\n\n DELETE widget: "+widget+"\n\n");
	widgetRepository.delete(widget);
}

@DeleteMapping("/api/topic/{topicId}/widget/delete")
public List<Widget> deleteAllWidgetsByTopic(@PathVariable("topicId") int topicId)
{
	Optional<Topic> data = topicRepository.findById(topicId);
		Topic topic = data.get();
		List<Widget> all = topic.getWidgets();
		 for(Widget w : all) {
			 System.out.println("\n\n DELETE widget: "+ w +"\n\n");
			 widgetRepository.delete(w);
		 }
		 
		 return all;
}


@PostMapping("/api/topic/{topicId}/widget")
public Widget addWidget(
		@PathVariable("topicId") int topicId,
		@RequestBody Widget widget) {
	Optional<Topic> data = topicRepository.findById(topicId);
	Topic topic = data.get();
		widget.setTopic(topic);
		return widgetRepository.save(widget);
}


@PostMapping("/api/topic/{topicId}/widget/save")
public void saveAllWidgets(
		@PathVariable("topicId") int topicId,
		@RequestBody List<Widget> widgets) {
	Optional<Topic> data = topicRepository.findById(topicId);
	Topic topic;
	for(Widget widget: widgets){
		 topic = data.get();
		widget.setTopic(topic);
		widgetRepository.save(widget);
	}
}


}