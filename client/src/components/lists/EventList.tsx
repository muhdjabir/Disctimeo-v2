import { Event } from "@/types/club"
import { CalendarIcon } from 'lucide-react'
import { Badge } from "@/components/ui/badge"

interface EventListProps {
    events: Event[]
}

export function EventList({ events }: EventListProps) {
    return (
        <div className="space-y-4">
            <h3 className="text-lg font-semibold">Upcoming Events</h3>
            <div className="space-y-4">
                {events.map((event) => (
                    <div key={event.id} className="flex items-center justify-between p-4 rounded-lg bg-muted">
                        <div className="flex items-center space-x-4">
                            <CalendarIcon className="h-6 w-6 text-muted-foreground" />
                            <div>
                                <p className="font-medium">{event.name}</p>
                                <p className="text-sm text-muted-foreground">{event.date}</p>
                            </div>
                        </div>
                        <Badge variant={event.type === 'Trial' ? 'secondary' : 'default'}>
                            {event.type}
                        </Badge>
                    </div>
                ))}
            </div>
        </div>
    )
}

