from fastapi import FastAPI
from fastapi.responses import HTMLResponse
from pydantic import BaseModel
import uvicorn
import requests
import json
import openai

app = FastAPI(docs_url=None)


class Request(BaseModel):
    question: str


class Response(BaseModel):
    answer: str


@app.get("/", response_class=HTMLResponse)
def server():
    html_file = open("index.html", "r").read()
    return html_file


@app.post("/qa")
def sendQA(request: Request):
    url = "https://api.openai.com/v1/completions"
    # 填 https://platform.openai.com/account/api-keys 网页上的 api keys
    token = 'your token'
    headers = {'content-type': 'application/json',
               'Authorization': 'Bearer '+token}
    data = {"model": "text-davinci-003", "prompt": request.question, "temperature": 0.8, "max_tokens": 2048}
    res = requests.post(url=url, json=data, headers=headers)
    res_json = json.loads(res.text)
    r = res_json["choices"][0]["text"]
    print(r)
    return {'answer': r.strip('\n')}

@app.post("/qa2")
def senQA2(request: Request):
    openai.api_key = 'your token'
    completion = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages=[
            {"role": "user", "content": request.question}
        ]
    )
    print(completion.choices[0].message)
    return {'answer': completion.choices[0].message.content.strip('\n')}

if __name__ == "__main__":
    uvicorn.run(app, host='0.0.0.0', port=8888)
