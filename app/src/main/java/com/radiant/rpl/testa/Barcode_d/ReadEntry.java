package com.radiant.rpl.testa.Barcode_d;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadEntry {
    String uid,name,yob;
    String ns;

    public ReadEntry(){}
    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "PrintLetterBarcodeData");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            //if (name.equals("entry")) {
            entries.add(readEntry(parser));
            // } else {
            // skip(parser);
            // }
        }
        return entries;
    }


    public ReadEntry(String uid, String name, String yob) {
        this.uid = uid;
        this.name = name;
        this.yob = yob;
    }

    ReadEntry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "");
        String title = null;
        String summary = null;
        String link = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("uid")) {
                title = readTitle(parser);
            } else if (name.equals("name")) {
                summary = readSummary(parser);
            } else if (name.equals("yob")) {
                link = readLink(parser);
            } else {
                // skip(parser);
            }
        }
        return new ReadEntry(title, summary, link);
    }

    // Processes title tags in the feed.
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "uid");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "uid");
        return title;
    }

    // Processes link tags in the feed.
    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, ns, "yob");
        String link = readText(parser);
        //String tag = parser.getName();
        /*String relType = parser.getAttributeValue(null, "yob");
        if (tag.equals("yob")) {
            if (relType.equals("yob")){
                link = parser.getAttributeValue(null, "yob");
                parser.nextTag();
            }
        }*/
        parser.require(XmlPullParser.END_TAG, ns, "yob");
        return link;
    }

    // Processes summary tags in the feed.
    private String readSummary(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String summary = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return summary;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        System.out.println("the result is"+result);
        return result;
    }
}
