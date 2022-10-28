from http.server import HTTPServer, BaseHTTPRequestHandler


class HttpHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()
        s = str(self.headers.values())
        print(s)
        self.wfile.write(s.encode("utf-8"))


try:
    httpd = HTTPServer(('127.0.0.1', 8000), HttpHandler)
    httpd.serve_forever()
except KeyboardInterrupt:
    print('^C received, shutting down the web server')
    httpd.socket.close()
