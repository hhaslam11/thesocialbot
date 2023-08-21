# The Social Bot
A slack bot that sends the occasional discussion question.


## How to use (Docker)

### Step 1
Create a [Slack app](https://api.slack.com/apps/) and get the token

### Step 2
Pull and run the [Docker image](https://hub.docker.com/repository/docker/hhaslam11/thesocialbot) with the following variables:
- `SLACK_BOT_TOKEN`: The token of the slack bot
- `SLACK_CHANNEL`: The channel to send the questions to
