import asyncio
import aiohttp


# 异步任务
async def job(session, url):
    name = url.split('/')[-1]
    # 碰到 await 切换协程，等待数据获取完毕
    img = await session.get(url)
    # 从网络中读取
    img_code = await img.read()
    # 写入保存
    # with open('/home/nathan/project/fa-py/' + str(name), 'wb') as f:
    with open(str(name), 'wb') as f:
        f.write(img_code)
    return str(url)


async def main(_loop, _urls):
    async with aiohttp.ClientSession() as session:
        # 任务
        tasks = [_loop.create_task(job(session, _urls[_])) for _ in range(2)]
        # 等待完成
        finished, unfinished = await asyncio.wait(tasks)
        all_results = [r.result() for r in finished]
        print("result:", str(all_results))


urls = ['https://pic1.zhimg.com/v2-f68d609e79d091f227818e5ec8665bc8_r.jpg',
        'https://img-blog.csdnimg.cn/20210622111024503.png']
loop = asyncio.get_event_loop()
loop.run_until_complete(main(loop, urls))
loop.close()
