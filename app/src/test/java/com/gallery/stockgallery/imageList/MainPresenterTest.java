package com.gallery.stockgallery.imageList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    @Mock
    MainView mainView;
    private MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        mainPresenter = new MainPresenter(mainView);
    }

    @Test
    public void testShouldOpenImagePreview() throws Exception {
        String url = "abc";
        mainPresenter.onClickImage(url);
        verify(mainView).viewImage(url);

    }
}