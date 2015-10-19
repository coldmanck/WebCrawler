# WebCrawler
Written in Java, WebCrawler is a crawling tool that can crawl arbitrary numbers of pages on designated page.

## Installation and Usage
To Install, use UNIX command line, 
```
$ javac WebCrawler.java
```
To use it,
```
$ java WebCrawler PAGES_LIMIT http[s]://...
```
For example, 
```
$ java WebCrawler 10 http://www.google.com
```
and the output is
```
Crawl http://www.google.com
Crawl http://schema.org/WebPage
Crawl http://www.google.com.tw/imghp?hl=zh-TW&tab=wi
Crawl http://www.google.com.tw/intl/zh-TW/services/
Crawl http://www.w3.org/1999/xhtml
Crawl http://schema.org/docs/documents.html
Crawl http://schema.org/docs/schemas.html
Crawl http://schema.org/
Crawl https://github.com/schemaorg/schemaorg/issues?q=is%3Aissue+is%3Aopen+WebPage
Crawl http://schema.org/breadcrumb
Crawl http://schema.org/lastReviewed
Crawling finish.
```

## License
It is released under MIT license. See the LICENSE file to see license rights and limitation).
