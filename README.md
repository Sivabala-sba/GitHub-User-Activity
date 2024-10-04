# GitHub User Activity CLI

This project is a simple Command Line Interface (CLI) tool built in Java that fetches and displays the recent activity of any GitHub user using the GitHub API. It allows users to track the activities like pushes, issues opened, starred repositories, etc., of any GitHub account in real-time directly from the terminal.

## Features

- Fetch the recent activity of any GitHub user.
- Display activities like:
    - Pushed commits.
    - Opened issues.
    - Starred repositories.
- Interactive command-line tool.
- Handles errors gracefully such as invalid usernames or API failures.

## How It Works

The CLI accepts a GitHub username as input, fetches the user's recent activities from the GitHub API, and displays them in a human-readable format in the terminal.


## Example Output

Enter GitHub username: kamranahmedse
Recent activity for GitHub user: kamranahmedse
Pushed 3 commit(s) to kamranahmedse/developer-roadmap
Opened a new issue in kamranahmedse/developer-roadmap
Starred kamranahmedse/developer-roadmap

## Future Enhancement

- Add features to filter activity by event types (e.g., only show push events).
- Display activity in a more structured format (e.g., with colors, tables).
- Cache the fetched activity data to improve performance.

## Contributing

If you find a bug or want to add a feature, feel free to create an issue or submit a pull request.

Project URL: [GitHub-User-Activity](https://roadmap.sh/projects/github-user-activity "GitHub-User-Activity")