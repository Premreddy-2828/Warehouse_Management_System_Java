import json
import html

with open("semgrep-report.json", "r", encoding="utf-8") as f:
    report = json.load(f)

results = report.get("results", [])

html_content = f"""
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Semgrep Security Report</title>
    <style>
        body {{
            font-family: Arial, sans-serif;
            margin: 40px;
        }}

        h1 {{
            margin-bottom: 10px;
        }}

        .summary {{
            padding: 15px;
            border: 1px solid #ccc;
            margin-bottom: 25px;
        }}

        .finding {{
            border: 1px solid #ccc;
            padding: 20px;
            margin-bottom: 20px;
        }}

        .severity {{
            font-weight: bold;
        }}

        pre {{
            background: #f4f4f4;
            padding: 10px;
            overflow-x: auto;
        }}
    </style>
</head>

<body>

<h1>Semgrep Security Report</h1>

<div class="summary">
    <strong>Total Findings:</strong> {len(results)}
</div>
"""

for finding in results:
    check_id = html.escape(finding.get("check_id", "Unknown"))
    path = html.escape(finding.get("path", "Unknown"))

    message = html.escape(
        finding.get("extra", {}).get("message", "No description")
    )

    severity = html.escape(
        finding.get("extra", {}).get("severity", "Unknown")
    )

    start_line = finding.get("start", {}).get("line", "Unknown")

    html_content += f"""
<div class="finding">

    <div class="severity">
        Severity: {severity}
    </div>

    <p><strong>Rule:</strong> {check_id}</p>

    <p><strong>File:</strong> {path}</p>

    <p><strong>Line:</strong> {start_line}</p>

    <p><strong>Description:</strong></p>

    <p>{message}</p>

</div>
"""

html_content += """
</body>
</html>
"""

with open("semgrep-report.html", "w", encoding="utf-8") as f:
    f.write(html_content)

print("HTML report generated: semgrep-report.html")
